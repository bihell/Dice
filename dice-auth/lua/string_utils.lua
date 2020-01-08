local _M = { _VERSION = '0.0.1' }

local json = require("cjson.safe")

function _M.sort_and_join_kv(self, args, join_symbol)
    ngx.log(ngx.ERR, type(args))
    ngx.log(ngx.ERR, json.encode(args))

    local args_str = ""

    if args == nil then
        return args_str
    end

    local args_keys = {}
    for k, _ in pairs(args) do
        ngx.log(ngx.ERR, k)
        table.insert(args_keys, k)
    end

    if table.getn(args_keys) == 0 then
        return args_str
    end

    table.sort(args_keys)
    for k, v in ipairs(args_keys) do
        if string.len(args_str) > 0 then
            args_str = args_str .. join_symbol
        end

        local value = ""
        if args[v] ~= nil then
            value = args[v]
        end

        ngx.log(ngx.ERR, v)
        ngx.log(ngx.ERR, value)

        args_str = args_str .. v .. "=" .. value
    end
    ngx.log(ngx.ERR, args_str)
    return args_str
end

--string check ,like StringUtils
function _M.string_not_null(self, s)
    return nil ~= s
end

function _M.string_null(self, s)
    return nil == s
end

function _M.string_not_empty(self, s)
    return _M:string_not_null(s) and string.len(s) > 0
end

function _M.string_empty(self, s)
    return _M:string_null(s) or string.len(s) < 1
end

--decode request param,for chinese and symbol
local function decode_uri(s)
    s = string.gsub(s, '%%(%x%x)', function(h)
        return string.char(tonumber(h, 16))
    end)
    return s
end

--encode request param,for chinese and symbol
local function encode_uri(s)
    s = string.gsub(s, "([^%w%.%- ])", function(c)
        return string.format("%%%02X", string.byte(c))
    end)
    return string.gsub(s, " ", "+")
end

--try decode parameter ,until equal or above max decode time 
function _M.try_decode(self, encode_body, max_decode_time)
    if nil == max_decode_time then
        max_decode_time = 5
    end
    local decode_body = decode_uri(encode_body)
    local after_decode = decode_body
    local repeat_time = 1

    while repeat_time < max_decode_time and decode_body ~= after_decode
    do
        decode_body = after_decode
        after_decode = decode_uri(decode_body)
        repeat_time = repeat_time + 1
    end
    return after_decode
end

function _M.split(self, szFullString, szSeparator)
    local nFindStartIndex = 1
    local nSplitIndex = 1
    local nSplitArray = {}
    while true do
        local nFindLastIndex = string.find(szFullString, szSeparator, nFindStartIndex)
        if not nFindLastIndex then
            nSplitArray[nSplitIndex] = string.sub(szFullString, nFindStartIndex, string.len(szFullString))
            break
        end
        nSplitArray[nSplitIndex] = string.sub(szFullString, nFindStartIndex, nFindLastIndex - 1)
        nFindStartIndex = nFindLastIndex + string.len(szSeparator)
        nSplitIndex = nSplitIndex + 1
    end
    return nSplitArray
end
return _M