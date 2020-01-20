package com.bihell.dice.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.mapper.auth.AuthItemMapper;
import com.bihell.dice.mapper.auth.AuthRelItemApiMapper;
import com.bihell.dice.model.auth.AuthItem;
import com.bihell.dice.model.auth.AuthRelItemApi;
import com.bihell.dice.service.auth.AuthItemService;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.UUID;

/**
 * @author haseochen
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthItemServiceImpl implements AuthItemService {
    private final AuthItemMapper authItemMapper;
    private final AuthRelItemApiMapper authRelItemApiMapper;

    @Override
    public AuthItem save(AuthItem authItem) {
        if (authItem.getItemCode() == null || authItem.getItemCode().trim().length() == 0) {
            String itemCode = UUID.randomUUID().toString().replace("-", "");
            authItem.setItemCode(itemCode);
        }
        Map<String, Object> param = ImmutableMap.of("item_code", authItem.getItemCode());
        Preconditions.checkArgument(authItemMapper.selectByMap(param).size() < 1, "ItemCode重复");
        authItem.insert();
        return authItem;
    }

    @Override
    public AuthItem update(AuthItem authItem) {
        Map<String, Object> param = ImmutableMap.of("item_code", authItem.getItemCode());
        Preconditions.checkArgument(authItemMapper.selectByMap(param).size() < 2, "ItemCode重复");
        authItem.updateById();
        return authItem;
    }

    @Override
    public void assignApi(AuthItem authItem) {
        authRelItemApiMapper.delete(new QueryWrapper<AuthRelItemApi>().lambda().eq(AuthRelItemApi::getItemId,authItem.getId()));
        if (CollectionUtils.isEmpty(authItem.getApiIds())) {
            return;
        }
        for (Integer apiId : authItem.getApiIds()) {
            if (apiId == null) {
                continue;
            }
            AuthRelItemApi authItemApi = new AuthRelItemApi().setApiId(apiId).setItemId(authItem.getId());
            authItemApi.insert();
        }
    }
}
