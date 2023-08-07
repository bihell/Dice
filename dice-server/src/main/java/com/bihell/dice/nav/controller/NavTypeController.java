package com.bihell.dice.nav.controller;

import com.bihell.dice.commons.api.ApiResult;
import com.bihell.dice.commons.controller.BaseController;
import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.commons.core.validator.groups.Add;
import com.bihell.dice.commons.core.validator.groups.Update;
import com.bihell.dice.commons.log.annotation.OperationLog;
import com.bihell.dice.commons.log.enums.OperationLogType;
import com.bihell.dice.commons.utils.SecurityUtil;
import com.bihell.dice.nav.entity.NavType;
import com.bihell.dice.nav.param.NavTypePageParam;
import com.bihell.dice.nav.service.NavTypeService;
import com.bihell.dice.nav.vo.NavTypeVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  控制器
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/nav/type")
public class NavTypeController extends BaseController {

    @Autowired
    private NavTypeService navDetailService;

    /**
     * 添加
     */
    @PreAuthorize("@auth.hasPermission('nav:type:add')")
    @PostMapping("/add")
    @OperationLog(name = "添加", type = OperationLogType.ADD)
    @Operation(summary = "添加")
    public ApiResult<Boolean> addNavType(@Validated(Add.class) @RequestBody NavType navType) throws Exception {
        navType.setCreator(SecurityUtil.getUser().getUserId());
        boolean flag = navDetailService.saveNavType(navType);
        return ApiResult.result(flag);
    }

    /**
     * 修改
     */
    @PreAuthorize("@auth.hasPermission('nav:type:update')")
    @PostMapping("/update")
    @OperationLog(name = "修改", type = OperationLogType.UPDATE)
    @Operation(summary = "修改")
    public ApiResult<Boolean> updateNavType(@Validated(Update.class) @RequestBody NavType navType) throws Exception {
        navType.setModifier(SecurityUtil.getUser().getUserId());
        boolean flag = navDetailService.updateNavType(navType);
        return ApiResult.result(flag);
    }

    /**
     * 删除
     */
    @PreAuthorize("@auth.hasPermission('nav:type:delete')")
    @PostMapping("/delete/{id}")
    @OperationLog(name = "删除", type = OperationLogType.DELETE)
    @Operation(summary = "删除")
    public ApiResult<Boolean> deleteNavType(@PathVariable("id") Long id) throws Exception {
        boolean flag = navDetailService.deleteNavType(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取详情
     */
    @GetMapping("/info/{id}")
    @OperationLog(name = "详情", type = OperationLogType.INFO)
    @Operation(summary = "详情")
    public ApiResult<NavType> getNavType(@PathVariable("id") Long id) throws Exception {
        NavType navDetail = navDetailService.getById(id);
        return ApiResult.success(navDetail);
    }

    /**
     * 分页列表
     */
    @PostMapping("/getPageList")
    @OperationLog(name = "分页列表", type = OperationLogType.PAGE)
    @Operation(summary = "分页列表")
    public ApiResult<Paging<NavType>> getNavTypePageList(@Validated @RequestBody NavTypePageParam navDetailPageParam) throws Exception {
        Paging<NavType> paging = navDetailService.getNavTypePageList(navDetailPageParam);
        return ApiResult.success(paging);
    }

    /**
     * 获取导航类型树形列表
     */
    @GetMapping("/getNavTypeTree")
    @OperationLog(name = "获取导航类型树形列表", type = OperationLogType.OTHER_QUERY)
    @Operation(summary = "获取导航类型树形列表")
    public ApiResult<List<NavTypeVo>> getDepartmentTree(@Validated NavTypePageParam navTypePageParam) throws Exception {
        List<NavTypeVo> treeVos = navDetailService.getNavTypeTree(navTypePageParam);
        return ApiResult.success(treeVos);
    }
}

