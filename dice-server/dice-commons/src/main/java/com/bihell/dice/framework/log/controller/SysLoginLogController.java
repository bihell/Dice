package com.bihell.dice.framework.log.controller;

import com.bihell.dice.framework.api.ApiResult;
import com.bihell.dice.framework.controller.BaseController;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.log.annotation.Module;
import com.bihell.dice.framework.log.annotation.OperationLog;
import com.bihell.dice.framework.log.entity.SysLoginLog;
import com.bihell.dice.framework.log.enums.OperationLogType;
import com.bihell.dice.framework.log.param.SysLoginLogPageParam;
import com.bihell.dice.framework.log.service.SysLoginLogService;
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
 * 系统登录日志 控制器
 */
@Slf4j
@RestController
@RequestMapping("/sysLoginLog")
@Module("log")
@Tag(name = "系统登录日志API")
// TODO: 2023/6/28 待重构
public class SysLoginLogController extends BaseController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 系统登录日志分页列表
     */
    @PostMapping("/getPageList")
    @PreAuthorize("@auth.hasPermission('sys:login:log:page')")
    @OperationLog(name = "系统登录日志分页列表", type = OperationLogType.PAGE)
    @Operation(summary = "系统登录日志分页列表")
    public ApiResult<Paging<SysLoginLog>> getSysLoginLogPageList(@Validated @RequestBody SysLoginLogPageParam sysLoginLogPageParam) throws Exception {
        Paging<SysLoginLog> paging = sysLoginLogService.getSysLoginLogPageList(sysLoginLogPageParam);
        return ApiResult.ok(paging);
    }

}

