package com.bihell.dice.service.impl;

import com.bihell.dice.mapper.AuthItemMapper;
import com.bihell.dice.model.domain.AuthItem;
import com.bihell.dice.service.AuthItemService;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
}
