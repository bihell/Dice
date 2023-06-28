package com.bihell.dice.framework.log.controller;

import com.bihell.dice.framework.api.ApiResult;
import com.bihell.dice.framework.controller.BaseController;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.log.entity.SysOperationLog;
import com.bihell.dice.framework.log.param.SysOperationLogPageParam;
import com.bihell.dice.framework.log.service.SysOperationLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统操作日志 控制器
 */
@Slf4j
@RestController
@RequestMapping("/sysOperationLog")
@Tag(name = "系统操作日志API")
public class SysOperationLogController extends BaseController {

    @Autowired
    private SysOperationLogService sysOperationLogService;

    /**
     * 系统操作日志分页列表
     */
    @PostMapping("/getPageList")
    @PreAuthorize("@auth.hasPermission('sys:operation:log:page')")
    @Operation(summary = "系统操作日志分页列表")
    public ApiResult<Paging<SysOperationLog>> getSysOperationLogPageList(@Validated @RequestBody SysOperationLogPageParam sysOperationLogPageParam) throws Exception {
        Paging<SysOperationLog> paging = sysOperationLogService.getSysOperationLogPageList(sysOperationLogPageParam);
        return ApiResult.ok(paging);
    }

}

