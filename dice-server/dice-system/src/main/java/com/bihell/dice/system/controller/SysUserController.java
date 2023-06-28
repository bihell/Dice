package com.bihell.dice.system.controller;

import com.bihell.dice.framework.api.ApiResult;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.core.validator.groups.Add;
import com.bihell.dice.framework.core.validator.groups.Update;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.system.dto.SysUserDto;
import com.bihell.dice.system.param.UserPageParam;
import com.bihell.dice.system.service.SysUserService;
import com.bihell.dice.system.vo.SysUserQueryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户 前端控制器
 * @author haseochen
 */
@Slf4j
@RestController
@Module("system")
@Tag(name = "系统用户API")
@RequestMapping("/v1/api/admin/auth/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SysUserController {
    private final SysUserService sysUserService;

//    private final TokenService tokenService;

    /**
     * 添加系统用户
     */
    @PostMapping("/add")
    @PreAuthorize("@auth.hasPermission('sys:user:add')")
    @OperationLog(name = "添加系统用户", type = OperationLogType.ADD)
    @Operation(summary = "添加系统用户")
    public ApiResult<String> addSysUser(@Validated(Add.class) @RequestBody SysUserDto sysUserDto) throws Exception {
        sysUserService.addUser(sysUserDto);
        return ApiResult.ok("创建成功");
    }

    /**
     * 修改系统用户
     */
    @PostMapping("/update")
    @PreAuthorize("@auth.hasPermission('sys:user:update')")
    @OperationLog(name = "修改系统用户", type = OperationLogType.UPDATE)
    @Operation(summary = "修改系统用户")
    public ApiResult<Boolean> updateSysUser(@Validated(Update.class) @RequestBody SysUserDto sysUserDto) throws Exception {
        boolean flag = sysUserService.updateUser(sysUserDto);
        return ApiResult.result(flag);
    }

    /**
     * 系统用户分页列表
     */
    @GetMapping("/getPageList")
    @PreAuthorize("@auth.hasPermission('sys:user:page')")
    @OperationLog(name = "系统用户分页列表", type = OperationLogType.PAGE)
    @Operation(summary = "系统用户分页列表")
    public ApiResult<Paging<SysUserQueryVo>> getSysUserPageList(@Validated UserPageParam sysUserPageParam) throws Exception {
        Paging<SysUserQueryVo> paging = sysUserService.getUserPageList(sysUserPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 删除系统用户
     */
    @PostMapping("/delete/{id}")
    @PreAuthorize("@auth.hasPermission('sys:user:delete')")
    @OperationLog(name = "删除系统用户", type = OperationLogType.DELETE)
    @Operation(summary = "删除系统用户")
    public ApiResult<Boolean> deleteSysUser(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysUserService.deleteSysUser(id);
        return ApiResult.result(flag);
    }

}
