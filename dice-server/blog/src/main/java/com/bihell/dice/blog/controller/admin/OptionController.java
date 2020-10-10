package com.bihell.dice.blog.controller.admin;

import com.bihell.dice.blog.service.blog.OptionService;
import com.bihell.dice.framework.common.api.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("all")
    public RestResponse getAllOptions() {
        return RestResponse.ok(optionService.getAllOptionMap());
    }

    /**
     * 保存所有设置
     *
     * @param options 设置key-value
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("save")
    public RestResponse saveAllOptions(@RequestParam Map<String, String> options) {
        optionService.save(options);
        return RestResponse.ok();
    }
}
