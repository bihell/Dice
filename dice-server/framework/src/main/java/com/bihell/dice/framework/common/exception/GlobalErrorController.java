package com.bihell.dice.framework.common.exception;

import com.bihell.dice.framework.common.api.ApiCode;
import com.bihell.dice.framework.common.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 全局Error/404处理 todo
 */
@ApiIgnore
@RestController
@Slf4j
public class GlobalErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ApiResult<?> handleError(HttpServletRequest request, HttpServletResponse response){
        int status = response.getStatus();
        switch (status){
            case HttpServletResponse.SC_UNAUTHORIZED:
                return ApiResult.fail(ApiCode.UNAUTHORIZED);
            case HttpServletResponse.SC_FORBIDDEN:
                return ApiResult.fail(ApiCode.NOT_PERMISSION);
            case HttpServletResponse.SC_NOT_FOUND:
                return ApiResult.fail(ApiCode.NOT_FOUND);
            default:
                break;
        }
        return ApiResult.fail(ApiCode.FAIL);
    }

    @Override
    public String getErrorPath() {
        log.error("errorPath....");
        return ERROR_PATH;
    }
}
