local _M = { _VERSION = '0.01' }

local func = require("func")

--数据库配置
local props = {
    host = "172.28.1.2",
    port = 3306,
    database = "dice",
    user = "root",
    password = "root"
}

function _M.get_db()
    local mysql = require("resty.mysql")
    local function close_db(db)
        if not db then
            return
        end
        db:close()
    end
    local db, err = mysql:new()
    if not db then
        func.error_exit(string.format('new mysql error: %s', err))
        return
    end
    db:set_timeout(1000)
    local res, err, errno, sqlstate = db:connect(props)
    if not res then
        close_db(db)
        func.error_exit(string.format('connect mysql error %s', err))
    end
    return db
end

function _M.open_db(db_props)
    local mysql = require("resty.mysql")
    local function close_db(db)
        if not db then
            return
        end
        db:close()
    end
    local db, err = mysql:new()
    if not db then
        error_exit(string.format('new mysql error: %s', err))
        return
    end
    db:set_timeout(1000)
    local res, err, errno, sqlstate = db:connect(db_props)
    if not res then
        close_db(db)
        error_exit(string.format('connect mysql error %s', err))
    end
    return db
end

function _M.close_db(db)
    if not db then
        return
    end
    db:close()
end

return _M
