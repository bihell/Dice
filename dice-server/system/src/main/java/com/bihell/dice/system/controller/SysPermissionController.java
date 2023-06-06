package com.bihell.dice.system.controller;

import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.common.controller.BaseController;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.system.entity.SysMenu;
import com.bihell.dice.system.param.SysPermissionPageParam;
import com.bihell.dice.system.service.SysMenuService;
import com.bihell.dice.system.service.SysRoleMenuService;
import com.bihell.dice.system.vo.SysPermissionVo;
import com.bihell.dice.system.vo.SysPermissionTreeVo;
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
 * 系统权限 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/auth/sysPermission")
@Module("system")
@Api(value = "系统权限 API", tags = {"系统权限"})
public class SysPermissionController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 添加系统权限
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:permission:add")
    @OperationLog(name = "添加系统权限", type = OperationLogType.ADD)
    @ApiOperation(value = "添加系统权限", response = ApiResult.class)
    public ApiResult<Boolean> addSysPermission(@Validated @RequestBody SysMenu sysMenu) throws Exception {
        boolean flag = sysMenuService.saveSysPermission(sysMenu);
        return ApiResult.result(flag);
    }

    /**
     * 修改系统权限
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:permission:update")
    @OperationLog(name = "添加系统权限", type = OperationLogType.UPDATE)
    @ApiOperation(value = "修改系统权限", response = ApiResult.class)
    public ApiResult<Boolean> updateSysPermission(@Validated @RequestBody SysMenu sysMenu) throws Exception {
        boolean flag = sysMenuService.updateSysPermission(sysMenu);
        return ApiResult.result(flag);
    }

    /**
     * 删除系统权限
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:permission:delete")
    @OperationLog(name = "删除系统权限", type = OperationLogType.DELETE)
    @ApiOperation(value = "删除系统权限", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysPermission(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysMenuService.deleteSysPermission(id);
        return ApiResult.result(flag);
    }

    /**
     * 系统权限详情
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:permission:info")
    @OperationLog(name = "系统权限详情", type = OperationLogType.INFO)
    @ApiOperation(value = "系统权限详情", notes = "", response = SysPermissionVo.class)
    public ApiResult<SysPermissionVo> getSysPermission(@PathVariable("id") Long id) throws Exception {
        SysPermissionVo sysPermissionVo = sysMenuService.getSysPermissionById(id);
        return ApiResult.ok(sysPermissionVo);
    }

    /**
     * 系统权限分页列表
     */
    @PostMapping("/getPageList")
    @RequiresPermissions("sys:permission:page")
    @OperationLog(name = "系统权限分页列表", type = OperationLogType.PAGE)
    @ApiOperation(value = "系统权限分页列表", response = SysPermissionVo.class)
    public ApiResult<Paging<SysPermissionVo>> getSysPermissionPageList(@Validated @RequestBody SysPermissionPageParam sysPermissionPageParam) throws Exception {
        Paging<SysPermissionVo> paging = sysMenuService.getSysPermissionPageList(sysPermissionPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取所有菜单列表
     * @return
     */
    @GetMapping("/getAllMenuList")
    @RequiresPermissions("sys:permission:all:menu:list")
    @OperationLog(name = "获取所有菜单列表", type = OperationLogType.LIST)
    @ApiOperation(value = "获取所有菜单列表", response = SysMenu.class)
    public ApiResult<List<SysMenu>> getAllMenuList() throws Exception {
        List<SysMenu> list = sysMenuService.getAllMenuList();
        return ApiResult.ok(list);
    }

    /**
     * 获取获取菜单树形列表
     * @return
     */
    @GetMapping("/getAllMenuTree")
    @RequiresPermissions("sys:permission:all:menu:tree")
    @OperationLog(name = "获取获取菜单树形列表", type = OperationLogType.OTHER_QUERY)
    @ApiOperation(value = "获取获取菜单树形列表", response = SysPermissionTreeVo.class)
    public ApiResult<List<SysPermissionTreeVo>> getAllMenuTree() throws Exception {
        List<SysPermissionTreeVo> treeVos = sysMenuService.getAllMenuTree();
        return ApiResult.ok(treeVos);
    }


    /**
     * 根据用户id获取菜单列表
     * @return
     */
    @PostMapping("/getMenuListByUserId/{userId}")
    @RequiresPermissions("sys:permission:menu:list")
    @OperationLog(name = "根据用户id获取菜单列表", type = OperationLogType.OTHER_QUERY)
    @ApiOperation(value = "根据用户id获取菜单列表", response = SysMenu.class)
    public ApiResult<List<SysMenu>> getMenuListByUserId(@PathVariable("userId") Long userId) throws Exception {
        List<SysMenu> list = sysMenuService.getMenuListByUserId(userId);
        return ApiResult.ok(list);
    }

    /**
     * 根据用户id获取菜单树形列表
     * @return
     */
    @PostMapping("/getMenuTreeByUserId/{userId}")
    @RequiresPermissions("sys:permission:menu:tree")
    @OperationLog(name = "根据用户id获取菜单树形列表", type = OperationLogType.OTHER_QUERY)
    @ApiOperation(value = "根据用户id获取菜单树形列表", response = SysPermissionTreeVo.class)
    public ApiResult<List<SysPermissionTreeVo>> getMenuTreeByUserId(@PathVariable("userId") Long userId) throws Exception {
        List<SysPermissionTreeVo> treeVos = sysMenuService.getMenuTreeByUserId(userId);
        return ApiResult.ok(treeVos);
    }

    /**
     * 根据用户id获取该用户所有权限编码
     * @return
     */
    @GetMapping("/getPermissionCodesByUserId/{userId}")
    @RequiresPermissions("sys:permission:codes")
    @OperationLog(name = "根据用户id获取该用户所有权限编码", type = OperationLogType.OTHER_QUERY)
    @ApiOperation(value = "根据用户id获取该用户所有权限编码", response = ApiResult.class)
    public ApiResult<List<String>> getPermissionCodesByUserId(@PathVariable("userId") Long userId) throws Exception {
        List<String> list = sysMenuService.getPermissionCodesByUserId(userId);
        return ApiResult.ok(list);
    }

    /**
     * 根据角色id获取该对应的所有三级权限ID
     * @return
     */
    @GetMapping("/getThreeLevelPermissionIdsByRoleId/{roleId}")
    @RequiresPermissions("sys:permission:three-ids-by-role-id")
    @OperationLog(name = "根据角色id获取该对应的所有三级权限ID", type = OperationLogType.OTHER_QUERY)
    @ApiOperation(value = "根据角色id获取该对应的所有三级权限ID", response = ApiResult.class)
    public ApiResult<List<Long>> getPermissionIdsByRoleId(@PathVariable("roleId") Long roleId) throws Exception {
        List<Long> list = sysRoleMenuService.getThreeLevelPermissionIdsByRoleId(roleId);
        return ApiResult.ok(list);
    }

    /**
     * 获取所有导航树形菜单(一级/二级菜单)
     * @return
     */
    @PostMapping("/getNavMenuTree")
    @RequiresPermissions("sys:permission:nav-menu")
    @OperationLog(name = "获取所有导航菜单(一级/二级菜单)", type = OperationLogType.OTHER_QUERY)
    @ApiOperation(value = "获取所有导航菜单(一级/二级菜单)", response = ApiResult.class)
    public ApiResult<List<SysPermissionTreeVo>> getNavMenuTree() throws Exception {
        List<SysPermissionTreeVo> list = sysMenuService.getNavMenuTree();
        return ApiResult.ok(list);
    }

}

