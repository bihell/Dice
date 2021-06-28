package com.bihell.dice.nav.controller;

import com.bihell.dice.framework.util.LoginUtil;
import com.bihell.dice.nav.entity.NavDetail;
import com.bihell.dice.nav.param.NavDetailPageParam;
import com.bihell.dice.nav.service.NavDetailService;
import lombok.extern.slf4j.Slf4j;
import com.bihell.dice.framework.common.controller.BaseController;
import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.framework.core.validator.groups.Add;
import com.bihell.dice.framework.core.validator.groups.Update;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = "导航明细表API", tags = {"导航明细表"})
public class NavDetailController extends BaseController {

    @Autowired
    private NavDetailService navDetailService;

    /**
     * 添加导航明细表
     */
    @PostMapping("/add")
    @OperationLog(name = "添加导航明细表", type = OperationLogType.ADD)
    @ApiOperation(value = "添加导航明细表", response = ApiResult.class)
    public ApiResult<Boolean> addNavDetail(@Validated(Add.class) @RequestBody NavDetail navDetail) throws Exception {
        navDetail.setCreator(LoginUtil.getUserId());
        boolean flag = navDetailService.saveNavDetail(navDetail);
        return ApiResult.result(flag);
    }

    /**
     * 修改导航明细表
     */
    @PostMapping("/update")
    @OperationLog(name = "修改导航明细表", type = OperationLogType.UPDATE)
    @ApiOperation(value = "修改导航明细表", response = ApiResult.class)
    public ApiResult<Boolean> updateNavDetail(@Validated(Update.class) @RequestBody NavDetail navDetail) throws Exception {
        navDetail.setModifier(LoginUtil.getUserId());
        boolean flag = navDetailService.updateNavDetail(navDetail);
        return ApiResult.result(flag);
    }

    /**
     * 删除导航明细表
     */
    @PostMapping("/delete/{id}")
    @OperationLog(name = "删除导航明细表", type = OperationLogType.DELETE)
    @ApiOperation(value = "删除导航明细表", response = ApiResult.class)
    public ApiResult<Boolean> deleteNavDetail(@PathVariable("id") Long id) throws Exception {
        boolean flag = navDetailService.deleteNavDetail(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取导航明细表详情
     */
    @GetMapping("/info/{id}")
    @OperationLog(name = "导航明细表详情", type = OperationLogType.INFO)
    @ApiOperation(value = "导航明细表详情", response = NavDetail.class)
    public ApiResult<NavDetail> getNavDetail(@PathVariable("id") Long id) throws Exception {
        NavDetail navDetail = navDetailService.getById(id);
        return ApiResult.ok(navDetail);
    }

    /**
     * 导航明细表分页列表
     */
    @GetMapping("/getPageList")
    @OperationLog(name = "导航明细表分页列表", type = OperationLogType.PAGE)
    @ApiOperation(value = "导航明细表分页列表", response = NavDetail.class)
    public ApiResult<Paging<NavDetail>> getNavDetailPageList(@Validated NavDetailPageParam navDetailPageParam) throws Exception {
        Paging<NavDetail> paging = navDetailService.getNavDetailPageList(navDetailPageParam);
        return ApiResult.ok(paging);
    }

}

