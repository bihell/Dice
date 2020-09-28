package com.bihell.dice.system.service;

import com.bihell.dice.system.entity.AuthApi;

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
