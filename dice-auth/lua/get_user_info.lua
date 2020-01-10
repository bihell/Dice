---
--- 查询用户信息
---

local func = require("func")

local user_info = ngx.req.get_headers()[".USERINFO"]
if user_info == nil then
    func.invalid_exit("user is null")
end

local user_info_json = ngx.decode_base64(user_info)

ngx.status = 200
ngx.header.content_type = "application/json; charset=utf-8";
ngx.say(string.format('{"code":0,"msg":null,"data":%s,"success":true}', user_info_json))
ngx.exit(ngx.OK)

