---
--- 权限校验
---

if ngx.var.request_method == 'OPTIONS' then
    return
end

-- access_by_lua_file 只能有一个，需要在执行auth_check之前加载login_check.lua,
local login_check = assert(loadfile('lua/login_check.lua'))
login_check()

local func = require("func")
local db_utils = require("db_utils")

local headers = ngx.req.get_headers()
local user_id = ngx.req.get_headers()[".USERID"]
local project_type = ngx.req.get_headers()[".PROJECTTYPE"]

--静态资源不进行安全验证
local project_path = ''
if project_type ~= nil then
    project_path = '/' .. project_type
end
local static_path = project_path .. '/static/'
local default_static_path = '/static/'
local icon_path = project_path .. '/favicon.ico'
local default_icon_path = '/favicon.ico'
-- 本地访问dev域名时app.js前没有/static
local app_js_path = '/app.js'
local health_check_path = '/do_not_delete/health_check'
local login_path ='/v1/api/admin/auth/login'
local logout_path='/v1/api/admin/auth/logout'

if ngx.var.uri and (func.end_with(ngx.var.uri, login_path) or ngx.var.uri == icon_path or ngx.var.uri == default_icon_path or func.start_with(ngx.var.uri, static_path) or func.start_with(ngx.var.uri, default_static_path) or func.start_with(ngx.var.uri, app_js_path) or func.end_with(ngx.var.uri, health_check_path) or func.end_with(ngx.var.uri, logout_path)) then
    return
end

if user_id == nil then
    func.invalid_exit("user is null")
end

-- 这里写死项目名字，以后有需要了调整
project_type='dice'
if project_type == nil then
    func.invalid_exit("project_type is null")
end

local content_type = (ngx.req.get_headers()['content-type'] or '')
local access_uri = ngx.var.uri
local json = require("cjson.safe")
json.encode_sparse_array(true)

ngx.log(ngx.INFO, 'project_type: ' .. project_type .. ' access_uri: ' .. access_uri .. ' content_type: ' .. content_type)
-- 先校验是不是管理员
local db = db_utils.get_db()
local sql = string.format('select 1 from auth_rel_role_user t join auth_role t1 on t.role_id=t1.role_id and t1.role_type=1 and t1.status=1 and t1.project_type="%s" where t.user_id=%s and t.status=1 limit 1', project_type, user_id)
ngx.log(ngx.INFO, '执行SQL查询: ' .. sql)
local res, err, errno, sqlstate = db:query(sql)
-- 当前系统的管理员
if res and res[1] then
    db_utils.close_db(db)
    return
end

-- 去掉uri上的点号，统一处理.json、.download等结尾的url
local dot_index = string.find(access_uri, '[.]')
if dot_index ~= nil then
    access_uri = string.sub(access_uri, 1, dot_index - 1)
end

-- 普通用户权限校验
if func.start_with(ngx.var.uri, '/api') or string.find(content_type, 'application/json') or project_type == 'stbp' or string.find(ngx.var.uri, '/v%d') then
    --接口类别权限
    -- 获取用户所有接口权限，根据item查找
    sql = string.format('select 1 from (SELECT 1 FROM auth_api t1 join auth_rel_item_api t2 on t1.api_id=t2.api_id and t2.status=1 join auth_item t3 on t2.item_id=t3.item_id and t3.status=1 join auth_rel_role_item t4 on t3.item_id=t4.item_id and t4.status=1 join auth_rel_role_user t5 on t4.role_id=t5.role_id and t5.status=1 where t1.status=1 and "%s" like concat(t1.api_path,"%%") and t1.project_type="%s" and t5.user_id=%s union all select 1 from auth_api t1 join auth_rel_item_api t2 on t1.api_id=t2.api_id and t2.status=1 join auth_item t3 on t2.item_id=t3.item_id and t3.status=1 join auth_classes t4 on t3.classes_id=t4.classes_id and t4.status=1 join auth_group t5 on t4.group_id=t5.group_id and t5.status=1 join auth_role t6 on t5.project_type=t6.project_type and t6.status=1 and t6.role_type=1 join auth_rel_role_user t7 on t6.role_id=t7.role_id where t1.status=1 and "%s" like concat(t1.api_path,"%%") and t1.project_type="%s" and t7.user_id=%s) t limit 1', access_uri, project_type, user_id, access_uri, project_type, user_id)
    ngx.log(ngx.INFO, '执行SQL查询: ' .. sql)

    res, err, errno, sqlstate = db:query(sql)
    if res == nil or not res or not res[1] then
        db_utils.close_db(db)
        ngx.log(ngx.WARN, '未授权访问: ' .. user_id)
        func.invalid_exit(access_uri)
    end
end

db_utils.close_db(db)
