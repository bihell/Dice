package com.bihell.dice.system.controller;

import com.bihell.dice.framework.common.api.RestResponse;
import com.bihell.dice.framework.common.exception.ErrorCode;
import com.bihell.dice.framework.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共类 Controller todo 需要调整
 *
 * @author bihell
 * @since 2017/7/8 10:25
 */
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected CacheUtil cacheUtil;

    protected RestResponse error404() {
        return RestResponse.fail(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMsg());
    }
}
