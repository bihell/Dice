package com.bihell.dice.nav.controller;

import com.bihell.dice.framework.api.ApiResult;
import com.bihell.dice.framework.controller.BaseController;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.core.validator.groups.Add;
import com.bihell.dice.framework.core.validator.groups.Update;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.framework.utils.SecurityUtils;
import com.bihell.dice.nav.entity.NavDetail;
import com.bihell.dice.nav.param.NavDetailPageParam;
import com.bihell.dice.nav.service.NavDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 导航明细表 控制器
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/nav/detail")
@Module("nav")
@Tag(name = "导航明细表API")
public class NavDetailController extends BaseController {

    @Autowired
    private NavDetailService navDetailService;

    /**
     * 添加导航明细表
     */
    @PostMapping("/add")
    @PreAuthorize("@auth.hasPermission('nav:detail:add')")
    @OperationLog(name = "添加导航明细表", type = OperationLogType.ADD)
    @Operation(summary = "添加导航明细表")
    public ApiResult<Boolean> addNavDetail(@Validated(Add.class) @RequestBody NavDetail navDetail) throws Exception {
        navDetail.setCreator(SecurityUtils.getUser().getUserId());
        boolean flag = navDetailService.saveNavDetail(navDetail);
        return ApiResult.result(flag);
    }

    /**
     * 修改导航明细表
     */
    @PreAuthorize("@auth.hasPermission('nav:detail:update')")
    @PostMapping("/update")
    @OperationLog(name = "修改导航明细表", type = OperationLogType.UPDATE)
    @Operation(summary = "修改导航明细表")
    public ApiResult<Boolean> updateNavDetail(@Validated(Update.class) @RequestBody NavDetail navDetail) throws Exception {
        navDetail.setModifier(SecurityUtils.getUser().getUserId());
        boolean flag = navDetailService.updateNavDetail(navDetail);
        return ApiResult.result(flag);
    }

    /**
     * 删除导航明细表
     */
    @PreAuthorize("@auth.hasPermission('nav:detail:delete')")
    @PostMapping("/delete/{id}")
    @OperationLog(name = "删除导航明细表", type = OperationLogType.DELETE)
    @Operation(summary = "删除导航明细表")
    public ApiResult<Boolean> deleteNavDetail(@PathVariable("id") Long id) throws Exception {
        boolean flag = navDetailService.deleteNavDetail(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取导航明细表详情
     */
    @GetMapping("/info/{id}")
    @OperationLog(name = "导航明细表详情", type = OperationLogType.INFO)
    @Operation(summary = "导航明细表详情")
    public ApiResult<NavDetail> getNavDetail(@PathVariable("id") Long id) throws Exception {
        NavDetail navDetail = navDetailService.getById(id);
        return ApiResult.ok(navDetail);
    }

    /**
     * 导航明细表分页列表
     */
    @GetMapping("/getPageList")
    @OperationLog(name = "导航明细表分页列表", type = OperationLogType.PAGE)
    @Operation(summary = "导航明细表分页列表")
    public ApiResult<Paging<NavDetail>> getNavDetailPageList(@Validated NavDetailPageParam navDetailPageParam) throws Exception {
        Paging<NavDetail> paging = navDetailService.getNavDetailPageList(navDetailPageParam);
        return ApiResult.ok(paging);
    }

}

