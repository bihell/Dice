local _M = { _VERSION = '0.0.1' }

local lrucache = require("resty.lrucache")

--创建缓存实例，并指定最多缓存多少条目
local cache, err = lrucache.new(200)

--[[
	set cache
	cache:cache dictionary
	key:cache key
	value:cache value
	exptime:expire time
]]
function _M.set(key, value, exptime)
    if not exptime then
        exptime = 0
    end
    local succ, err, forcible = cache:set(key, value, exptime)
    return succ
end


--[[
	read cache
	cache:cache dictionary
	key:cache key
]]
function _M.get(key)
    return cache:get(key)
end

return _M

