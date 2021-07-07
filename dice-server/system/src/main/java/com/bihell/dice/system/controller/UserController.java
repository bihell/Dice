package com.bihell.dice.system.controller;

import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.core.validator.groups.Add;
import com.bihell.dice.framework.core.validator.groups.Update;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.system.entity.SysUser;
import com.bihell.dice.system.param.UserPageParam;
import com.bihell.dice.system.service.UserService;
import com.bihell.dice.system.vo.SysUserQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ApiResult<Boolean> addSysUser(@Validated(Add.class) @RequestBody SysUser sysUser) throws Exception {
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
    public ApiResult<Boolean> updateSysUser(@Validated(Update.class) @RequestBody SysUser sysUser) {
        boolean flag = userService.updateUser(sysUser);
        return ApiResult.result(flag);
    }

    /**
     * 系统用户分页列表
     */
    @GetMapping("/getPageList")
//    @RequiresPermissions("sys:user:page")
    @OperationLog(name = "系统用户分页列表", type = OperationLogType.PAGE)
    @ApiOperation(value = "系统用户分页列表", response = SysUserQueryVo.class)
    public ApiResult<Paging<SysUserQueryVo>> getSysUserPageList(@Validated UserPageParam sysUserPageParam) throws Exception {
        Paging<SysUserQueryVo> paging = userService.getUserPageList(sysUserPageParam);
        return ApiResult.ok(paging);
    }

}
