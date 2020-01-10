local _M = { _VERSION = '0.0.1' }

_M.ERROR_RESPONSE = [[{"status":0,"message":"%s","err_code":"%s"}]]

_M.ERR_ENUM = {
    ["E-1001"] = "Unauthorized",
    ["E-1002"] = "Signature not equal",
    ["E-1003"] = "Differ time out of range",
    ["E-1004"] = "Parameter illegality",
    ["E-1005"] = "Url illegality"
}

return _M