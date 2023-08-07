package com.bihell.dice.auth.security.handle;

import com.bihell.dice.commons.api.ApiCode;
import com.bihell.dice.commons.api.ApiResult;
import com.bihell.dice.commons.utils.ServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证入口
 *
 * @author Tang
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    /**
     * 认证失败
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        var apiResult = ApiResult.fail(ApiCode.UNAUTHORIZED, "认证失败，无法访问：" + request.getRequestURI());
        var objectMapper = new ObjectMapper();
        var objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        var json = objectWriter.writeValueAsString(apiResult);
        ServletUtil.renderString(response, json);
    }

}
