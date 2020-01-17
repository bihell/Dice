package com.bihell.dice.service.auth;

import com.bihell.dice.model.auth.AuthApi;


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
