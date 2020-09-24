package com.bihell.dice.framework.common.controller;

import com.bihell.dice.framework.log.annotation.OperationLogIgnore;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 项目根路径提示信息 todo
 * </p>
 */
@Slf4j
@Controller
@OperationLogIgnore
@Api(value = "Index API", tags = {"Index"})
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }

    /**
     * SwaggerUI
     */
    @GetMapping("/docs")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }

}
