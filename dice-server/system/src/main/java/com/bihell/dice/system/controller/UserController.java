package com.bihell.dice.system.controller;

import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.core.validator.groups.Add;
import com.bihell.dice.framework.core.validator.groups.Update;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.system.entity.User;
import com.bihell.dice.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户 前端控制器
 */
@Slf4j
@RestController
@Module("system")
@Api(value = "系统用户API", tags = {"系统用户"})
@RequestMapping("/v1/api/admin/auth/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserService userService;

    /**
     * 添加系统用户
     */
    @PostMapping("/add")
//    @RequiresPermissions("sys:user:add") todo
    @OperationLog(name = "添加系统用户", type = OperationLogType.ADD)
    @ApiOperation(value = "添加系统用户", response = ApiResult.class)
    public ApiResult<Boolean> addSysUser(@Validated(Add.class) @RequestBody User sysUser) throws Exception {
        boolean flag = userService.addUser(sysUser);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统用户(具有管理权限的用户才能使用）
     */
    @PostMapping("/update")
//    @RequiresPermissions("sys:user:update") todo
    @OperationLog(name = "修改系统用户", type = OperationLogType.UPDATE)
    @ApiOperation(value = "修改系统用户",notes = "具有管理权限的用户才能使用",response = ApiResult.class)
    public ApiResult<Boolean> updateSysUser(@Validated(Update.class) @RequestBody User user) {
        boolean flag = userService.updateUser(user);
        return ApiResult.result(flag);
    }

}
