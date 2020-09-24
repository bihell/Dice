package com.bihell.dice.framework.shiro.jwt;

import com.bihell.dice.framework.shiro.cache.LoginRedisService;
import com.bihell.dice.framework.shiro.vo.LoginSysUserRedisVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Shiro 授权认证 todo
 **/
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    private LoginRedisService loginRedisService;

    public JwtRealm(LoginRedisService loginRedisService) {
        this.loginRedisService = loginRedisService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof JwtToken;
    }

    /**
     * 授权认证,设置角色/权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("doGetAuthorizationInfo principalCollection...");
        // 设置角色/权限信息
        JwtToken jwtToken = (JwtToken) principalCollection.getPrimaryPrincipal();
        // 获取username
        String username = jwtToken.getUsername();
        // 获取登录用户角色权限信息
        LoginSysUserRedisVo loginSysUserRedisVo = loginRedisService.getLoginSysUserRedisVo(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 设置角色
        authorizationInfo.setRoles(SetUtils.hashSet(loginSysUserRedisVo.getRoleCode()));
        // 设置权限
        authorizationInfo.setStringPermissions(loginSysUserRedisVo.getPermissionCodes());
        return authorizationInfo;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("doGetAuthenticationInfo authenticationToken...");
        // 校验token
        JwtToken jwtToken = (JwtToken) authenticationToken;
        if (jwtToken == null) {
            throw new AuthenticationException("jwtToken不能为空");
        }
        String salt = jwtToken.getSalt();
        if (StringUtils.isBlank(salt)) {
            throw new AuthenticationException("salt不能为空");
        }
        return new SimpleAuthenticationInfo(
                jwtToken,
                salt,
                getName()
        );

    }

}
