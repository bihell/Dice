package com.bihell.dice.auth.security.authentication.username;

import com.bihell.dice.auth.web.service.authentication.AuthenticationService;
import com.bihell.dice.framework.model.UserModel;
import com.bihell.dice.system.service.SysUserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 用户名密码身份验证
 *
 * @author Tang
 */
@Component
public class UsernameAuthenticationProvider implements AuthenticationProvider {

    private final SysUserService userService;

    private final AuthenticationService authenticationService;

    public UsernameAuthenticationProvider(SysUserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        var authenticationToken = (UsernameAuthenticationToken) authentication;
        var username = authenticationToken.getPrincipal().toString();
        var password = authenticationToken.getCredentials().toString();

        var user = userService.selectUserByUsername(username);

        // TODO: 2023/6/26 这里登录方式以后增加邮件
        UserModel userModel = null;
        try {
            userModel = authenticationService.createUserModel(user, password, username, "username");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        authenticationToken = new UsernameAuthenticationToken(userModel, Collections.emptyList());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernameAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
