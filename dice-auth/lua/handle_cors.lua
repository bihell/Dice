ngx.header["Access-Control-Allow-Credentials"] = "true"
local headers = ngx.req.get_headers()
local origin_url = (headers['origin'] or headers['referer'])
if origin_url then
    local origin = string.sub(origin_url, 1, (string.find(origin_url, '/', 10) or string.len(origin_url) + 1) - 1)
    ngx.header["Access-Control-Allow-Origin"] = origin
    ngx.header["Access-Control-Max-Age"] = "86400"
    ngx.header["Access-Control-Allow-Methods"] = "GET, POST, OPTIONS, PUT, DELETE"
    ngx.header["Access-Control-Allow-Headers"] = "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range, userid, agent, brandid, language, token"
end
