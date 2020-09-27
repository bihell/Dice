package com.bihell.dice.system.service.impl;


import com.bihell.dice.system.entity.UserDetail;
import com.bihell.dice.system.service.Authentication;

/**
 * Authentication implementation.
 *
 * @author johnniang
 */
public class AuthenticationImpl implements Authentication {

    private final UserDetail userDetail;

    public AuthenticationImpl(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public UserDetail getDetail() {
        return userDetail;
    }
}
