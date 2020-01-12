---
--- 公共方法
---

local _M = { _VERSION = '0.0.1' }

function _M.redirect_login()
    -- 跳转PASS登陆
    local login_page = ngx.var.scheme .. '://' .. ngx.var.host .. ngx.var.request_uri
    ngx.log(ngx.INFO, 'redirect login page from ', login_page)
    ngx.redirect('/admin/login?redirect=' .. ngx.escape_uri(login_page))
end

function _M.redirect_static_page(page, username)
    --跳转静态页面
    local url = '/static/' .. page .. '?url=' .. ngx.var.request_uri .. '&username=' .. (username or '')
    ngx.log(ngx.INFO, 'redirect static page ', url)
    ngx.redirect(url)
end

function _M.invalid_exit(info)
    ngx.log(ngx.ERR, string.format('invalid access: %s', info))
    ngx.status = 401
    ngx.header.content_type = "application/json; charset=utf-8";
    ngx.say(string.format([[{"code":-2,"msg":"invalid access. %s","success":false}]], info))
    ngx.exit(ngx.OK)
end

function _M.error_exit(err)
    ngx.log(ngx.ERR, string.format('Server Error: %s', err))
    ngx.status = 500
    ngx.header.content_type = "application/json; charset=utf-8"
    ngx.say(string.format([[{"code":-1,"msg":"Server Error. %s","success":false}]], err))
    ngx.exit(ngx.OK)
end

function _M.start_with(str, flag)
    return string.sub(str, 1, string.len(flag)) == flag
end

function _M.end_with(str, flag)
    return string.sub(str, -string.len(flag)) == flag
end

function _M.get_prefix(domain, flag)
    return string.sub(domain, 1, ((string.find(domain, flag or '[.]')) or string.len(domain) + 1) - 1)
end

function _M.delete_prefix(domain, flag)
    return string.sub(domain, ((string.find(domain, flag or '[.]')) or 0) + 1)
end

return _M
