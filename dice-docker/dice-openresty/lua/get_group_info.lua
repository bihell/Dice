---
--- 查询菜单和权限项
---
local func = require("func")
local json = require("cjson.safe")
local db_utils = require("db_utils")
local user_id = ngx.req.get_headers()[".USERID"]
local args = ngx.req.get_uri_args()
local project_type = args['project']

if user_id == nil or project_type == nil then
    func.invalid_exit("user is null")
end

-- 如果是管理员，返回所有菜单和权限项
local db = db_utils.get_db()
local sql = string.format('select 1 from auth_rel_role_user t join auth_role t1 on t.role_id=t1.role_id and t1.role_type=1 and t1.status=1 and t1.project_type="%s" where t.user_id=%s and t.status=1 limit 1', project_type, user_id)
ngx.log(ngx.INFO, '执行SQL查询: ' .. sql)
local res, err, errno, sqlstate = db:query(sql)
-- 当前系统的管理员
if res and res[1] then
    sql = string.format('select t.group_id g_group_id,t.group_name g_group_name,t.group_url g_group_url,t.project_type g_project_type,t.order g_order,t.is_display g_is_display,t.style g_style,t1.classes_id c_classes_id,t1.classes_name c_classes_name,t1.classes_url c_classes_url,t1.order c_order,t1.is_display c_is_display,t1.style c_style,t2.item_id i_item_id,t2.item_name i_item_name,t2.item_code i_item_code,t2.order i_order,t2.style i_style,t2.outer_url i_outer_url from auth_group t join auth_classes t1 on t1.group_id=t.group_id and t1.status=1 join auth_item t2 on t2.classes_id=t1.classes_id and t2.status=1 where t.status=1 and t.project_type="%s" order by t.order,t1.order,t2.order', project_type)
else
    sql = string.format('select t.group_id g_group_id,t.group_name g_group_name,t.group_url g_group_url,t.project_type g_project_type,t.order g_order,t.is_display g_is_display,t.style g_style,t1.classes_id c_classes_id,t1.classes_name c_classes_name,t1.classes_url c_classes_url,t1.order c_order,t1.is_display c_is_display,t1.style c_style,t2.item_id i_item_id,t2.item_name i_item_name,t2.item_code i_item_code,t2.order i_order,t2.style i_style,t2.outer_url i_outer_url from auth_group t join auth_classes t1 on t1.group_id=t.group_id and t1.status=1 join auth_item t2 on t2.classes_id=t1.classes_id and t2.status=1 join auth_rel_role_item t3 on t3.item_id=t2.item_id and t3.status=1 join auth_rel_role_user t4 on t4.role_id=t3.role_id and t4.status=1 where t.status=1 and t.project_type="%s" and t4.user_id=%s order by t.order,t1.order,t2.order', project_type, user_id)
end
ngx.log(ngx.INFO, '执行SQL查询: ' .. sql)

local res, err, errno, sqlstate = db:query(sql)
if not res[1] then
    db_utils.close_db(db)
    ngx.log(ngx.INFO, string.format('【%s】未授权访问', user_id))
    func.invalid_exit('未授权访问')
end
db_utils.close_db(db)

local function comp(a, b)
    if a == nil or b == nil or a['order'] == nil or a['order'] == ngx.null or b['order'] == nil or b['order'] == ngx.null then
        return false
    end
    -- 两个值相当返回true会报错
    if a['order'] == b['order'] then
        return false
    end

    return a['order'] < b['order']
end

local group_map = {}
for k, v in ipairs(res) do
    local group = {}
    group["group_id"] = res[k]["g_group_id"]
    group["group_name"] = res[k]["g_group_name"]
    group["group_url"] = res[k]["g_group_url"]
    group["project_type"] = res[k]["g_project_type"]
    group["order"] = res[k]["g_order"]
    group["is_display"] = res[k]["g_is_display"]
    group["style"] = res[k]["g_style"]
    if group_map[group["group_id"]] == nil then
        group_map[group["group_id"]] = group
    end

    local classes = {}
    classes["group_id"] = res[k]["g_group_id"]
    classes["classes_id"] = res[k]["c_classes_id"]
    classes["classes_name"] = res[k]["c_classes_name"]
    classes["classes_url"] = res[k]["c_classes_url"]
    classes["order"] = res[k]["c_order"]
    classes["is_display"] = res[k]["c_is_display"]
    classes["style"] = res[k]["c_style"]
    if group_map[group["group_id"]]["children"] == nil then
        group_map[group["group_id"]]["children"] = {}
    end
    if group_map[group["group_id"]]["children"][classes["classes_id"]] == nil then
        group_map[group["group_id"]]["children"][classes["classes_id"]] = classes
    end
    if res[k]["i_item_id"] ~= nil and res[k]["i_item_id"] ~= ngx.null then
        local item = {}
        item["classes_id"] = res[k]["c_classes_id"]
        item["item_id"] = res[k]["i_item_id"]
        item["item_name"] = res[k]["i_item_name"]
        item["item_code"] = res[k]["i_item_code"]
        item["order"] = res[k]["i_order"]
        item["style"] = res[k]["i_style"]
        item["outer_url"] = res[k]["i_outer_url"]
        if group_map[group["group_id"]]["children"][classes["classes_id"]]["children"] == nil then
            group_map[group["group_id"]]["children"][classes["classes_id"]]["children"] = {}
        end
        group_map[group["group_id"]]["children"][classes["classes_id"]]["children"][item["item_id"]] = item
    end
end

local group_list = {}
for _, v in pairs(group_map) do
    local classes_list = {}
    for _, v1 in pairs(v["children"]) do
        if v1["children"] ~= nil then
            local item_list = {}
            for _, v2 in pairs(v1["children"]) do
                table.insert(item_list, v2)
            end
            table.sort(item_list, comp)
            v1["children"] = item_list
        end
        table.insert(classes_list, v1)
    end
    table.sort(classes_list, comp)
    v["children"] = classes_list
    table.insert(group_list, v)
end

table.sort(group_list, comp)

ngx.status = 200
ngx.header.content_type = "application/json; charset=utf-8";
ngx.say(string.format('{"code":0,"msg":null,"data":%s,"success":true}', json.encode(group_list)))
ngx.exit(ngx.OK)
