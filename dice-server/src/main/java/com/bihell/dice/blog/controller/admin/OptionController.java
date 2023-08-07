package com.bihell.dice.blog.controller.admin;

import com.bihell.dice.blog.service.blog.OptionService;
import com.bihell.dice.commons.api.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author bihell
 * @since 2019-05-21 21:14
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/option")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OptionController {

    private final OptionService optionService;

    /**
     * 获取所有设置
     *
     * @return Map
     */
    @GetMapping
    @PreAuthorize("@auth.hasPermission('blog:option:list')")
    public ApiResult<Map<String, String>> getAllOptions() {
        return ApiResult.success(optionService.getAllOptionMap());
    }

    /**
     * 保存所有设置
     *
     * @param options 设置key-value
     * @return {@see RestResponse.ok()}
     */
    @PostMapping
    @PreAuthorize("@auth.hasPermission('blog:option:update')")
    public ApiResult<Boolean> saveAllOptions(@Valid @RequestBody Map<String, String> options) {
        optionService.save(options);

        return ApiResult.success();
    }
}
