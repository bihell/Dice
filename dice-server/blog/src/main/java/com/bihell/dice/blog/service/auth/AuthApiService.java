package com.bihell.dice.blog.service.auth;

import com.bihell.dice.blog.model.auth.AuthApi;


/**
 * @author haseochen
 */
public interface AuthApiService {

    /**
     * 增加 api
     * @param authApi
     * @return
     */
    void add(AuthApi authApi);

    AuthApi update(AuthApi authApi);
}
