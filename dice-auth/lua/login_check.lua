---
--- 登录校验
---

if ngx.var.request_method == 'OPTIONS' then
    return
end

local func = require("func")

--根据域名获取子系统
local function get_project_type(domain, access_uri)
    local project = func.get_prefix(domain)
    if func.start_with(project, "yz") or func.start_with(project, "qa") then
        project = string.sub(project, 3)
    end
    if project == "data" then
        --如果是项目为data，则取uri中的第一个path
        project = func.get_prefix(string.sub(access_uri, 2), '/')
    end
    return project
end

-- local project_type = get_project_type(ngx.var.host, ngx.var.uri)
-- if project_type == nil or project_type == "unknown" then
--     func.error_exit("unknown project")
-- end

-- ngx.req.set_header('.PROJECTTYPE', project_type)


--静态资源不进行安全验证
-- local project_path = ''
-- if project_type ~= nil then
--     project_path = '/' .. project_type
-- end
-- local static_path = project_path .. '/static/'
local default_static_path = '/static/'
-- local icon_path = project_path .. '/favicon.ico'
local default_icon_path = '/favicon.ico'
-- 本地访问dev域名时app.js前没有/static
local app_js_path = '/app.js'
local health_check_path = '/do_not_delete/health_check'
local login_path='/v1/api/admin/auth/login'
if ngx.var.uri and (ngx.var.uri == default_icon_path or ngx.var.uri == default_icon_path or func.start_with(ngx.var.uri, default_static_path) or func.start_with(ngx.var.uri, default_static_path) or func.start_with(ngx.var.uri, app_js_path) or func.end_with(ngx.var.uri, health_check_path) or func.end_with(ngx.var.uri, login_path)) then
    return
end

local json = require("cjson.safe")

-- -- 验证PASS登录
local function verify_pass_login()


    local http = require "resty.http"
    local httpc = http.new()
    local res, err = httpc:request_uri("http://127.0.0.1:9091/v1/api/admin/auth/user_info", {
        method = "GET",
        headers = {
            ["Content-Type"] = "application/json; charset=utf-8",
            ["Authorization"] = ngx.req.get_headers()['Authorization']
        }
    })
    if not res or not res.body or res.status ~= ngx.HTTP_OK then
        ngx.log(ngx.ERR, err)
        func.error_exit(err)
    end

    local result = json.decode(res.body)

    ngx.log(ngx.INFO,result.data.id)

    if not result or result.code ~= 0 then
        ngx.log(ngx.INFO, string.format('get pass token fail %s',res.body))
        func.redirect_login()
    end

    ngx.log(ngx.INFO, string.format('get pass info : %s', res.body))

    local auth_data = result.data
    local auth_data_json = json.encode(result.data)

    local auth_cache = ngx.shared.auth_cache
    local succ, err, forcible = auth_cache:set(ngx.req.get_headers()['Authorization'], auth_data_json, 6)
    ngx.log(ngx.INFO, string.format('Get pass token success: %s [set cache: %s - %s - %s]', auth_data_json, succ, err, forcible))

    return auth_data, auth_data_json
end

-- 权限校验：C端权限，验证是否登录；B端权限，验证header是否携带token

if ngx.req.get_headers()['Authorization'] ~= nil then
    local auth_data_json = ngx.shared.auth_cache:get(ngx.req.get_headers()['Authorization'])
    local auth_data = nil
    if not auth_data_json then
        ngx.log(ngx.INFO, 'PASS授权信息缓存失效，重新获取用户信息')
        auth_data, auth_data_json = verify_pass_login()
    else
        auth_data = json.decode(auth_data_json)
    end

    ngx.req.set_header('.USERID', auth_data.id)
    ngx.req.set_header('.USERINFO', ngx.encode_base64(auth_data_json))
    ngx.req.set_header('.USERNAME', auth_data.username)

    ngx.log(ngx.INFO, auth_data_json)

else
    func.redirect_login()
end
