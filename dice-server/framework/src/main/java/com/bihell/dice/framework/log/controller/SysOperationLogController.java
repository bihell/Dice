package com.bihell.dice.framework.log.controller;

import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.common.controller.BaseController;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.log.entity.SysOperationLog;
import com.bihell.dice.framework.log.param.SysOperationLogPageParam;
import com.bihell.dice.framework.log.service.SysOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统操作日志 控制器 todo
 */
@Slf4j
@RestController
@RequestMapping("/sysOperationLog")
@Api(value = "系统操作日志API", tags = {"系统操作日志"})
public class SysOperationLogController extends BaseController {

    @Autowired
    private SysOperationLogService sysOperationLogService;

    /**
     * 系统操作日志分页列表
     */
    @PostMapping("/getPageList")
    @RequiresPermissions("sys:operation:log:page")
    @ApiOperation(value = "系统操作日志分页列表", response = SysOperationLog.class)
    public ApiResult<Paging<SysOperationLog>> getSysOperationLogPageList(@Validated @RequestBody SysOperationLogPageParam sysOperationLogPageParam) throws Exception {
        Paging<SysOperationLog> paging = sysOperationLogService.getSysOperationLogPageList(sysOperationLogPageParam);
        return ApiResult.ok(paging);
    }

}

