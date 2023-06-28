package com.bihell.dice.auth.security.handle;

import com.bihell.dice.auth.web.service.TokenService;
import com.bihell.dice.framework.api.ApiResult;
import com.bihell.dice.framework.utils.ServletUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登出入口
 *
 * @author Tang
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private final TokenService tokenService;

    public LogoutSuccessHandlerImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 登出成功
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var userModel = tokenService.get(request);
        if (userModel != null) {
            tokenService.delete(userModel.getToken());
        }

        var apiResult = ApiResult.ok("登出成功");
        var objectMapper = new ObjectMapper();
        var objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        var json = objectWriter.writeValueAsString(apiResult);
        ServletUtils.renderString(response, json);
    }

}
