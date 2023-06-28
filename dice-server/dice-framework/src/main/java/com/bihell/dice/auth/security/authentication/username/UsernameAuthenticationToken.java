package com.bihell.dice.auth.security.authentication.username;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 用户名密码身份令牌
 *
 * @author Tang
 */
public class UsernameAuthenticationToken extends AbstractAuthenticationToken {

    @java.io.Serial
    private static final long serialVersionUID = 5746900743403374870L;

    private final transient Object principal;

    private final transient Object credentials;

    public UsernameAuthenticationToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public UsernameAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = null;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

}
