package com.bihell.dice.system.controller;

import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.common.controller.BaseController;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.system.entity.SysDept;
import com.bihell.dice.system.param.SysDepartmentPageParam;
import com.bihell.dice.system.service.SysDeptService;
import com.bihell.dice.system.vo.SysDepartmentVo;
import com.bihell.dice.system.vo.SysDepartmentTreeVo;
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
 * 部门 前端控制器
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/auth/sysDepartment")
@Module("system")
@Api(value = "系统部门API", tags = {"系统部门"})
public class SysDepartmentController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 添加部门
     */
    @PostMapping("/add")
    @RequiresPermissions("sys:department:add")
    @OperationLog(name = "添加部门", type = OperationLogType.ADD)
    @ApiOperation(value = "添加部门", response = ApiResult.class)
    public ApiResult<Boolean> addSysDepartment(@Validated @RequestBody SysDept sysDept) throws Exception {
        boolean flag = sysDeptService.saveSysDepartment(sysDept);
        return ApiResult.result(flag);
    }

    /**
     * 修改部门
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:department:update")
    @OperationLog(name = "修改部门", type = OperationLogType.UPDATE)
    @ApiOperation(value = "修改部门", response = ApiResult.class)
    public ApiResult<Boolean> updateSysDepartment(@Validated @RequestBody SysDept sysDept) throws Exception {
        boolean flag = sysDeptService.updateSysDepartment(sysDept);
        return ApiResult.result(flag);
    }

    /**
     * 删除部门
     */
    @PostMapping("/delete/{id}")
    @RequiresPermissions("sys:department:delete")
    @OperationLog(name = "删除部门", type = OperationLogType.DELETE)
    @ApiOperation(value = "删除部门", response = ApiResult.class)
    public ApiResult<Boolean> deleteSysDepartment(@PathVariable("id") Long id) throws Exception {
        boolean flag = sysDeptService.deleteSysDepartment(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取部门
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:department:info")
    @OperationLog(name = "部门详情", type = OperationLogType.INFO)
    @ApiOperation(value = "部门详情", response = SysDepartmentVo.class)
    public ApiResult<SysDepartmentVo> getSysDepartment(@PathVariable("id") Long id) throws Exception {
        SysDepartmentVo sysDepartmentVo = sysDeptService.getSysDepartmentById(id);
        return ApiResult.ok(sysDepartmentVo);
    }

    /**
     * 部门分页列表
     */
    @PostMapping("/getPageList")
    @RequiresPermissions("sys:department:page")
    @OperationLog(name = "部门分页列表", type = OperationLogType.PAGE)
    @ApiOperation(value = "部门分页列表", response = SysDepartmentVo.class)
    public ApiResult<Paging<SysDepartmentVo>> getSysDepartmentPageList(@Validated @RequestBody SysDepartmentPageParam sysDepartmentPageParam) throws Exception {
        Paging<SysDepartmentVo> paging = sysDeptService.getSysDepartmentPageList(sysDepartmentPageParam);
        return ApiResult.ok(paging);
    }

    /**
     * 获取所有部门列表
     */
    @PostMapping("/getAllDepartmentList")
    @RequiresPermissions("sys:department:all:list")
    @OperationLog(name = "获取所有部门的树形列表", type = OperationLogType.OTHER_QUERY)
    @ApiOperation(value = "获取所有部门的树形列表", response = SysDept.class)
    public ApiResult<List<SysDept>> getAllDepartmentList() throws Exception {
        List<SysDept> list = sysDeptService.getAllDepartmentList();
        return ApiResult.ok(list);
    }

    /**
     * 获取所有部门的树形列表
     *
     * @return
     */
    @GetMapping("/getDepartmentTree")
//    @RequiresPermissions("sys:department:all:tree")
    @OperationLog(name = "获取所有部门的树形列表", type = OperationLogType.OTHER_QUERY)
    @ApiOperation(value = "获取所有部门的树形列表", response = SysDepartmentTreeVo.class)
    public ApiResult<List<SysDepartmentTreeVo>> getDepartmentTree() throws Exception {
        List<SysDepartmentTreeVo> treeVos = sysDeptService.getDepartmentTree();
        return ApiResult.ok(treeVos);
    }

    /**
     * 部门列表
     */
    @PostMapping("/getList")
    @RequiresPermissions("sys:department:list")
    @OperationLog(name = "部门列表", type = OperationLogType.LIST)
    @ApiOperation(value = "部门列表", response = SysDept.class)
    public ApiResult<List<SysDept>> getSysDepartmentList() throws Exception {
        List<SysDept> list = sysDeptService.list();
        return ApiResult.ok(list);
    }

}

