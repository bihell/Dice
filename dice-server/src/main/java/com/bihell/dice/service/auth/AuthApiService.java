package com.bihell.dice.service.auth;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.model.domain.AuthApi;
import com.bihell.dice.model.params.QueryParam;


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
