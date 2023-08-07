package com.bihell.dice.foobar.controller;

import com.bihell.dice.auth.annotation.Permission;
import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.commons.api.ApiResult;
import com.bihell.dice.foobar.dto.FooBarAddDTO;
import com.bihell.dice.foobar.dto.FooBarUpdateDTO;
import com.bihell.dice.foobar.req.FooBarRequest;
import com.bihell.dice.foobar.service.FooBarService;
import com.bihell.dice.foobar.resp.FooBarDetailVo;
import com.bihell.dice.foobar.resp.FooBarResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.Valid;

/**
 * FooBar 控制器
 *
 * @author tpxcer
 * @since 2023-07-31
 */
@Slf4j
@RestController
@RequestMapping("/foobar/foobar")
@Tag(name = "FooBar")
public class FooBarController {

    @Autowired
    private FooBarService fooBarService;

    /**
     * 添加FooBar
     *
     * @param fooBarAddDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/addfoobar")
    @Operation(summary = "添加FooBar")
    @Permission("foo:bar:add")
    public ApiResult addFooBar(@Valid @RequestBody FooBarAddDTO fooBarAddDTO) throws Exception {
        boolean flag = fooBarService.addFooBar(fooBarAddDTO);
        return ApiResult.result(flag);
    }

    /**
     * 修改FooBar
     *
     * @param fooBarUpdateDTO
     * @return
     * @throws Exception
     */
    @PostMapping("/updatefoobar")
    @Operation(summary = "修改FooBar")
    @Permission("foo:bar:update")
    public ApiResult updateFooBar(@Valid @RequestBody FooBarUpdateDTO fooBarUpdateDTO) throws Exception {
        boolean flag = fooBarService.updateFooBar(fooBarUpdateDTO);
        return ApiResult.result(flag);
    }

    /**
     * 删除FooBar
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/deletefoobar/{id}")
    @Operation(summary = "删除FooBar")
    @Permission("foo:bar:delete")
    public ApiResult deleteFooBar(@PathVariable Long id) throws Exception {
        boolean flag = fooBarService.deleteFooBar(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取FooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/getfoobarinfo/{id}")
    @Operation(summary = "FooBar详情")
    @Permission("foo:bar:info")
    public ApiResult<FooBarDetailVo> getFooBar(@PathVariable Long id) throws Exception {
        FooBarDetailVo fooBarDetailVo = fooBarService.getFooBarById(id);
        return ApiResult.success(fooBarDetailVo);
    }

    /**
     * FooBar分页列表
     *
     * @param fooBarRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/getfoobarpage")
    @Operation(summary = "FooBar分页列表")
    @Permission("foo:bar:list")
    public ApiResult<FooBarResponse> getFooBarList(@Valid @RequestBody FooBarRequest fooBarRequest) throws Exception {
        Paging<FooBarResponse> paging = fooBarService.getFooBarList(fooBarRequest);
        return ApiResult.success(paging);
    }

}
