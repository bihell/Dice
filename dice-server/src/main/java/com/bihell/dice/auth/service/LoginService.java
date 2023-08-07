package com.bihell.dice.auth.service;

import com.bihell.dice.auth.security.authentication.username.UsernameAuthenticationToken;
import com.bihell.dice.commons.model.LoginModel;
import com.bihell.dice.commons.model.UserModel;
import com.bihell.dice.system.service.SysLogLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 登陆服务
 *
 * @author Tang
 */
@Component
@Slf4j
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final SysLogLoginService logLoginService;

    public LoginService(AuthenticationManager authenticationManager, TokenService tokenService, SysLogLoginService logLoginService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.logLoginService = logLoginService;
    }

    /**
     * 登陆
     *
     * @param loginModel 登陆用户信息
     * @return token
     */
    public String login(LoginModel loginModel) {
        Authentication authentication;
        AbstractAuthenticationToken authenticationToken;

        authenticationToken = new UsernameAuthenticationToken(loginModel.getUsername(), loginModel.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        var account = authenticationToken.getPrincipal().toString();
        authentication = authenticationManager.authenticate(authenticationToken);

        var userModel = (UserModel) authentication.getPrincipal();

        var token = tokenService.createToken(userModel);

        logLoginService.recordLoginInfo(userModel.getUser().getUserId(), userModel, account, loginModel.getLoginType(), true, "登陆成功");
        log.info("用户使用 {} 方式登陆成功，登陆账号：{}", loginModel.getLoginType(), account);

        return token;
    }

}
