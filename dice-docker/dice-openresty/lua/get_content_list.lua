---
---获取内容
---

local func = require("func")
local json = require("cjson.safe")

local headers = ngx.req.get_headers()
local user_id = headers[".USERID"]
local args = ngx.req.get_uri_args()
local project_type = args['project']
if user_id == nil or project_type == nil then
    func.invalid_exit("user is null")
end

local db_utils = require("db_utils")
local db = db_utils.get_db()
local sql = string.format('select t2.content_type,t2.content_name,t2.content_value from rel_user_role t left join rel_role_content t1 on t1.role_id=t.role_id left join auth_content_value t2 on t2.id=t1.content_id where t.user_id=%s and t2.project_type="%s"', user_id, project_type)
local res, err, errno, sqlstate = db:query(sql)
if not res[1] then
    db_utils.close_db(db)
    ngx.log(ngx.INFO, string.format('【%s】未授权访问', auth_data.real_name))
    func.invalid_exit('未授权访问')
end
db_utils.close_db(db)

ngx.status = 200
ngx.header.content_type = "application/json; charset=utf-8";
ngx.say(string.format('{"data": {"cost": 0,"result": %s,"size": 0},"message": "success","status": 0}', json.encode(res)))
ngx.exit(ngx.OK)
