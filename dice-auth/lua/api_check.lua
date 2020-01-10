local _M = { _VERSION = '0.0.1' }

--local headers = ngx.req.get_headers()
local max_decode_time = 5
--skip all check
local skip_check = false
--skip sign check
local need_check_sign = true
--max time difference  default:60's
local request_diff_second = 120
--white list of ignore check. ignore action code
--local ignore_check_whitelist = {}
--cache config
--cache key
local cache_key = "com:bihell:dice:check_sign"
--cache expire time default:60's
local cache_expire_second = 300

local hmac = require "hmac"
local cache_client = require "cache_client"
local string_utils = require "string_utils"
local common_constants = require "common_constants"

local json = require("cjson.safe")
local db_utils = require("db_utils")

local function explode (_str, seperator)
    local pos, arr = 0, {}
    for st, sp in function()
        return string.find(_str, seperator, pos, true)
    end do
        table.insert(arr, string.sub(_str, pos, st - 1))
        pos = sp + 1
    end
    table.insert(arr, string.sub(_str, pos))
    return arr
end

local function init_post_args()
    local post_args_str = ""
    local request_method = ngx.var.request_method
    --if "POST" == request_method then
    local body = {}
    local file_args = {}
    local post_args_body = {}
    local is_have_file_param = false
    ngx.req.read_body()
    --判断是否是multipart/form-data类型的表单
    if string.sub(ngx.req.get_headers()["content-type"], 1, 20) == "multipart/form-data;" then
        is_have_file_param = true
        content_type = ngx.req.get_headers()["content-type"]
        --body可是符合http协议的请求体，不是普通的字符串
        body = ngx.req.get_body_data()
        --请求体的size大于nginx配置里的client_body_buffer_size，则会导致请求体被缓冲到磁盘临时文件里，client_body_buffer_size默认是8k或者16k
        if not body then
            local datafile = ngx.req.get_body_file()
            if not datafile then
                error_code = 1
                error_msg = "no request body found"
            else
                local fh, err = io.open(datafile, "r")
                if not fh then
                    error_code = 2
                    error_msg = "failed to open " .. tostring(datafile) .. "for reading: " .. tostring(err)
                else
                    fh:seek("set")
                    body = fh:read("*a")
                    fh:close()
                    if body == "" then
                        error_code = 3
                        error_msg = "request body is empty"
                    end
                end
            end
        end
        local new_body_data = {}
        --确保取到请求体的数据
        if not error_code then
            local boundary = "--" .. string.sub(ngx.req.get_headers()["content-type"], 31)
            local split_pos = string.find(boundary, "charset")
            if split_pos ~= nil then
                boundary = string.sub(boundary, 1, (split_pos - 3))
            end
            local body_data_table = explode(tostring(body), boundary)
            local first_string = table.remove(body_data_table, 1)
            local last_string = table.remove(body_data_table)
            for i, v in ipairs(body_data_table) do
                local start_pos, end_pos, capture, capture2 = string.find(v, 'Content%-Disposition: form%-data; name="(.+)"; filename="(.*)"')
                --普通参数
                if not start_pos then
                    local t = explode(v, "\r\n\r\n")
                    local temp_param_name = string.sub(t[1], 41, -2)
                    local temp_param_value = string.sub(t[2], 1, -3)
                    post_args_body[temp_param_name] = temp_param_value
                else
                    --文件类型的参数，capture是参数名称，capture2是文件名
                    file_args[capture] = capture2
                    table.insert(new_body_data, v)
                end
            end
            table.insert(new_body_data, 1, first_string)
            table.insert(new_body_data, last_string)
            --body可是符合http协议的请求体，不是普通的字符串
            body = table.concat(new_body_data, boundary)
        end
    elseif string.find(ngx.req.get_headers()["content-type"], 'form-urlencoded', 1) then
        -- form-urlencoded
        post_args_body = ngx.req.get_post_args()
    else
        -- json
        post_args_body = json.decode(ngx.req.get_body_data())
    end

    --local post_args_str = string_utils:sort_and_join_kv(post_args_body, "&")
    --post_args_str = string_utils:try_decode(post_args_body, max_decode_time)
    --if type(post_args_body) == "table" then
    --    -- 获取post中的json用ngx.req.get_body_data()
    --    local json = require("cjson.safe")
    --
    --
    --    decode_body = string_utils:try_decode(post_args_sort, max_decode_time)
    --else
    --    -- 获取post中的formdata用ngx.req.get_post_args()
    --    if string_utils:string_not_empty(post_args_body) then
    --
    --    end
    --end
    --end
    return post_args_body
end

local function parse_request_header()
    local sign = ngx.req.get_headers()["SIGN"]
    local timestamp = ngx.req.get_headers()["TIMESTAMP"]
    local partner_code = ngx.req.get_headers()["PARTNER-CODE"]

    if string_utils:string_not_empty(sign) then
        sign = string_utils:try_decode(sign, max_decode_time)
    end

    if string_utils:string_not_empty(timestamp) then
        timestamp = string_utils:try_decode(timestamp, max_decode_time)
    end

    if string_utils:string_not_empty(partner_code) then
        partner_code = string_utils:try_decode(partner_code, max_decode_time)
    end

    return sign, timestamp, partner_code
end

local function generate_sign_string (timestamp, partner_code, body_args, uri_args)
    local body_args_str = string_utils:sort_and_join_kv(body_args, "&")
    local uri_args_str = string_utils:sort_and_join_kv(uri_args, "&")
    --local decode_query_args_str = string_utils:try_decode(uri_args, max_decode_time)
    local check_sign_str = timestamp .. ":" .. partner_code
    if body_args_str ~= nil and string.len(body_args_str) > 0 then
        check_sign_str = check_sign_str .. ":" .. body_args_str
    end
    if uri_args_str ~= nil and string.len(uri_args_str) > 0 then
        check_sign_str = check_sign_str .. ":" .. uri_args_str
    end
    return check_sign_str
end

local function check_sign(request_sign, check_sign_str, secret_key)
    local hmac_sha1 = hmac:new(secret_key, hmac.ALGOS.SHA256)
    if not hmac_sha1 then
        logger:log_error("failed to create the hmac_sha1 object")
        return false
    end

    local ok = hmac_sha1:update(check_sign_str)
    if not ok then
        ngx.log(ngx.ERR, "hmac failed to add data")
        return false
    end

    local sign = ngx.encode_base64(hmac_sha1:final())

    ngx.log(ngx.INFO, check_sign_str)
    ngx.log(ngx.INFO, sign)
    ngx.log(ngx.INFO, request_sign)

    return sign == request_sign
end

local function query_secret_key(partner_code)
    local db = db_utils.get_db()
    local sql = string.format('select secret_key from system_user where status=1 and partner_code="%s"', partner_code)
    local res, err, errno, sqlstate = db:query(sql)
    if not res[1] then
        db_utils.close_db(db)
        ngx.log(ngx.INFO, string.format('【%s】未授权访问', user_id))
        func.invalid_exit('未授权访问')
    end
    db_utils.close_db(db)

    local res_data = res[1]
    return res_data.secret_key

    --local cache = ngx.shared.partner_secret_key
    --
    --local secret_cache_key = cache_key .. ":" .. partner_code .. ":" .. partner_code
    --
    --local secret_key = ""
    --
    --if nil ~= cache then
    --    secret_key = cache_client:get_cache(cache, secret_cache_key)
    --end
    --
    --if string_utils:string_empty(secret_key) then
    --    ngx.log(ngx.INFO, string.format("start prepare cache p:%s ....", partner_code))
    --    -- TODO: fusing mechanism
    --    local ret, db_object = pcall(function()
    --        return db_util.get_db()
    --    end
    --    )
    --    if not ret then
    --        ngx.log(ngx.ERR, "db connection has error")
    --        return "", true
    --    end
    --
    --    // TODO
    --local quote_partner_code = ngx.quote_sql_str(partner_code)
    --local res, err, errno, sqlstate = db_object:query(string.format('select secret_key from system_user where status=1 and partner_code="%s"', quote_partner_code))
    --if nil ~= err then
    ---- TODO: fusing mechanism
    --pcall(close_db, db_object)
    --ngx.log(ngx.ERR, "sql has error")
    --return "", true
    --end
    --if #res <= 0 then
    --if nil ~= cache then
    --cache_client:set_cache(cache, secret_cache_key, "", cache_expire_second)
    --end
    ---- TODO: fusing mechanism
    --pcall(close_db, db_object)
    --ngx.log(ngx.ERR, "unauthorized")
    --return "", false
    --end
    --
    --secret_key = res[1].secret_key
    --
    --local ret, return_value = pcall(close_db, db_object)
    --
    --if not ret then
    --ngx.log(ngx.ERR, "db connection has error")
    --end
    --
    --if nil ~= cache then
    --cache_client:set_cache(cache, secret_cache_key, secret_key, cache_expire_second)
    --end
    --
    --ngx.log(ngx.INFO, string.format("end prepare cache p:%s ....", partner_code))
    --end
    --return secret_key, false
    --return "bi-token-2017"
end

local function check_timestamp(time)
    local current_time = os.time()
    local time_diff = current_time - time
    return -request_diff_second < time_diff and time_diff < request_diff_second
end


--processing args
function _M.check_api_sign()
    if skip_check then
        return ngx.exit(0)
    end

    --parse request info
    local request_sign, timestamp, partner_code = parse_request_header()

    if string_utils:string_empty(timestamp) then
        ngx.log(ngx.ERR, "parameter error")
        ngx.say(string.format(common_constants.ERROR_RESPONSE, "E-1004", common_constants.ERR_ENUM["E-1004"]))
        return ngx.exit(200)
    end

    --check request time
    if not check_timestamp(timestamp) then
        ngx.log(ngx.ERR, "invalid timestamp")
        ngx.say(string.format(common_constants.ERROR_RESPONSE, "E-1003", common_constants.ERR_ENUM["E-1003"]))
        return ngx.exit(200)
    end

    --skip sign check ?
    if not need_check_sign then
        ngx.log(ngx.INFO, "skip check")
        return ngx.exit(0)
    end

    --if sign attribute not in request header, skip check
    if string_utils:string_empty(request_sign) then
        ngx.log(ngx.INFO, "skip check")
        return ngx.exit(0)
    end

    if string_utils:string_empty(partner_code) then
        ngx.log(ngx.INFO, "parameter error")
        ngx.say(string.format(common_constants.ERROR_RESPONSE, "E-1004", common_constants.ERR_ENUM["E-1004"]))
        return ngx.exit(200)
    end

    -- 获取uri上的参数，不支持编码后的url
    local uri_args = ngx.req.get_uri_args()
    --local query_args = ngx.req.get_uri_args()
    --if string_utils:string_not_empty(ngx.var.args) then
    --    local equal_symbol_index = string.find(ngx.var.args, '&', 1)
    --    if not equal_symbol_index then
    --        local convert_query_args = string_utils:try_decode(ngx.var.args, 1)
    --        convert_query_args = string.gsub(convert_query_args, '&', ',')
    --        local convert1 = string_utils:split(convert_query_args, ',')
    --        local args_table = {}
    --        for i, v in ipairs(convert1) do
    --            local tmp = convert1[i]
    --            local equal_symbol_index = string.find(tmp, '=', 1)
    --            if equal_symbol_index > 1 then
    --                local key = string.sub(tmp, 1, equal_symbol_index - 1)
    --                local value = string.sub(tmp, equal_symbol_index + 1, string.len(tmp))
    --                args_table[key] = value
    --            end
    --        end
    --        query_args = args_table
    --    end
    --end

    local body_args = init_post_args()

    -- 待签名字符串
    local check_sign_str = generate_sign_string(timestamp, partner_code, body_args, uri_args)

    local secret_key = query_secret_key(partner_code)

    --if nil ~= interrupt and interrupt then
    --    ngx.log(ngx.ERR, "has db error, skip check")
    --    return ngx.exit(0)
    --end

    --check secret key validation
    if string_utils:string_empty(secret_key) then
        ngx.log(ngx.ERR, "invalid secret_key")
        ngx.say(string.format(common_constants.ERROR_RESPONSE, "E-1001", common_constants.ERR_ENUM["E-1001"]))
        return ngx.exit(200)
    end

    local check_result = check_sign(request_sign, check_sign_str, secret_key)

    return check_result

end
--ngx.say(ngx.var.upstream_name)
--ngx.say(ngx.var.uri)
--ngx.say("end")

return _M
