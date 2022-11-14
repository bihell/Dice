package com.bihell.dice.system.controller;

import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.common.controller.BaseController;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.core.validator.groups.Add;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.system.entity.SysPermission;
import com.bihell.dice.system.entity.SysRole;
import com.bihell.dice.system.param.sysrole.SysRolePageParam;
import com.bihell.dice.system.param.sysrole.UpdateSysRolePermissionParam;
import com.bihell.dice.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 * 系统角色 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/auth/sysRole")
@Module("system")
@Api(value = "系统角色API", tags = {"系统角色"})
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 添加系统角色
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:role:add")
    @OperationLog(name = "添加系统角色", type = OperationLogType.ADD)
    @ApiOperation(value = "添加系统角色", response = ApiResult.class)
    public ApiResult<Boolean> addSysRole(@Validated(Add.class) @RequestBody SysRole sysRole) throws Exception {
        boolean flag = sysRoleService.saveSysRole(sysRole);
        return ApiResult.result(flag);
    }

//    /**
//     * 修改系统角色
//     */
//    @PostMapping("/update")
//    @RequiresPermissions("sys:role:update")
//    @OperationLog(name = "修改系统角色", type = OperationLogType.UPDATE)
//    @ApiOperation(value = "修改系统角色", response = ApiResult.class)
//    public ApiResult<Boolean> updateSysRole(@Validated(Update.class) @RequestBody SysRole sysRole) throws Exception {
//        boolean flag = sysRoleService.updateSysRole(sysRole);
//        return ApiResult.result(flag);
//    }

    /**
     * 删除系统角色
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:role:delete")
    @OperationLog(name = "删除系统角色", type = OperationLogType.DELETE)
    @ApiOperation(value = "删除系统角色", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysRole(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysRoleService.deleteSysRole(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取系统角色
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:role:info")
    @OperationLog(name = "系统角色详情", type = OperationLogType.INFO)
    @ApiOperation(value = "系统角色详情", response = SysRole.class)
    public ApiResult<SysRole> getSysRole(@PathVariable("id") Long id) throws Exception {
        SysRole sysRole = sysRoleService.getById(id);
        return ApiResult.ok(sysRole);
    }

    /**
     * 系统角色分页列表
     */
    @GetMapping("/getPageList")
//    @RequiresPermissions("sys:role:page")
    @OperationLog(name = "系统角色分页列表", type = OperationLogType.PAGE)
    @ApiOperation(value = "系统角色分页列表", response = SysRole.class)
    public ApiResult<Paging<SysRole>> getSysRolePageList(@Validated SysRolePageParam sysRolePageParam) throws Exception {
        Paging<SysRole> paging = sysRoleService.getSysRolePageList(sysRolePageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取系统角色列表
     *
     * @return
     */
    @GetMapping ("/getList")
//    @RequiresPermissions("sys:role:list")
    @OperationLog(name = "系统角色列表", type = OperationLogType.LIST)
    @ApiOperation(value = "系统角色列表", response = SysRole.class)
    public ApiResult<List<SysRole>> getRoleList() {
        return ApiResult.ok(sysRoleService.list());
    }

    /**
     * 修改系统角色权限
     * todo 只更新了权限，角色本身信息没更新，有时间补上
     */
    @PostMapping("/updateSysRolePermission")
    @RequiresPermissions("sys:role-permission:update")
    @OperationLog(name = "修改系统角色权限", type = OperationLogType.UPDATE)
    @ApiOperation(value = "修改系统角色权限", response = ApiResult.class)
    public ApiResult<Boolean> updateSysRolePermission(@Validated @RequestBody UpdateSysRolePermissionParam param) throws Exception {
        boolean flag = sysRoleService.updateSysRolePermission(param);
        return ApiResult.result(flag);
    }

    /**
     * 查询角色关联的菜单
     */
    @GetMapping("/listRoleMenus")
    public ApiResult<List<SysPermission>> listRoleMenus(@RequestParam String roleId) {
        return ApiResult.ok(sysRoleService.listRoleMenus(roleId));
    }

    // 批量删除
    // 多个id值 [1,2,3]
    // json数组格式 --- java的list集合
    //    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public ApiResult batchRemove(@RequestBody List<Long> ids) {
        sysRoleService.removeByIds(ids);
        return ApiResult.ok();
    }

}

