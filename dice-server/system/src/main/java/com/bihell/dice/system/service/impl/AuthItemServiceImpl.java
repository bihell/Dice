package com.bihell.dice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.system.entity.AuthItem;
import com.bihell.dice.system.entity.AuthRelItemApi;
import com.bihell.dice.system.mapper.AuthItemMapper;
import com.bihell.dice.system.mapper.AuthRelItemApiMapper;
import com.bihell.dice.system.service.AuthItemService;
import com.bihell.dice.system.service.AuthRelItemApiService;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private final AuthRelItemApiService authRelItemApiService;

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
        authRelItemApiMapper.delete(new QueryWrapper<AuthRelItemApi>().lambda().eq(AuthRelItemApi::getItemId, authItem.getId()));
        if (!CollectionUtils.isEmpty(authItem.getApiIds())) {
            List<AuthRelItemApi> authRelItemApiList = authItem.getApiIds().stream()
                    .filter(Objects::nonNull)
                    .map(i -> new AuthRelItemApi().setApiId(i).setItemId(authItem.getId()))
                    .collect(Collectors.toList());
            authRelItemApiService.saveBatch(authRelItemApiList);
        }
    }
}
