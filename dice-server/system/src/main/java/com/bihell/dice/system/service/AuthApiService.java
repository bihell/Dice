package com.bihell.dice.system.service;

import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.system.entity.AuthApi;
import com.bihell.dice.system.param.ApiPageParam;

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

    /**
     * 获取Api列表
     * @param apiPageParam
     * @return
     */
    Paging<AuthApi> getApiPageList(ApiPageParam apiPageParam);
}
