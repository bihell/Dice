---
--- 登录校验
---
if ngx.var.request_method == 'OPTIONS' then
    return
end

local func = require("func")


--静态资源不进行安全验证

local login_path='/v1/api/admin/auth/login'
local admin_path='/v1/api/admin'
local one_auth_path='/v1/api/one_auth'
-- 非/v1/api/admin请求、登录不做安全验证
if ngx.var.uri and ((not func.start_with(ngx.var.uri, admin_path) and not func.start_with(ngx.var.uri, one_auth_path)) or func.end_with(ngx.var.uri, login_path)) then
    return
end
local json = require("cjson.safe")

-- -- 验证PASS登录
local function verify_pass_login()


    local http = require "resty.http"
    local httpc = http.new()
    local res, err = httpc:request_uri("http://172.28.1.3:9091/v1/api/admin/auth/user_info", {
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

    if not result or result.code == 999 then
        ngx.log(ngx.INFO, string.format('get pass token fail %s',res.body))
        -- func.redirect_login()
        ngx.say('{"code":999,"msg":"Token Expired or Not Exist","data":null,"success":false}')
        ngx.exit(ngx.OK)
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
