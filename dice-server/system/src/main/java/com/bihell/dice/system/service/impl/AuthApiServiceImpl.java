package com.bihell.dice.system.service.impl;

import com.bihell.dice.system.entity.AuthApi;
import com.bihell.dice.system.mapper.AuthApiMapper;
import com.bihell.dice.system.service.AuthApiService;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * @author haseochen
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthApiServiceImpl implements AuthApiService {

    private final AuthApiMapper authApiMapper;

    /**
     * 增加 api
     *
     * @param authApi
     * @return
     */
    @Override
    public void add(AuthApi authApi) {
        Map<String, Object> param = ImmutableMap.of("api_path", authApi.getApiPath(), "project_type", authApi.getProjectType());
        Preconditions.checkArgument(authApiMapper.selectByMap(param).size() < 1, "Api 地址重复");
        authApi.insert();
    }

    @Override
    public AuthApi update(AuthApi authApi) {
        Map<String, Object> param = ImmutableMap.of("api_path", authApi.getApiPath(), "project_type", authApi.getProjectType());
        Preconditions.checkArgument(authApiMapper.selectByMap(param).size() < 2, "Api 地址重复");
        authApi.updateById();
        return authApi;
    }
}
