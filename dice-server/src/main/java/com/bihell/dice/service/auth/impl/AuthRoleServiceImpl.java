package com.bihell.dice.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.mapper.auth.AuthItemMapper;
import com.bihell.dice.mapper.auth.AuthRelRoleApiMapper;
import com.bihell.dice.mapper.auth.AuthRelRoleContentMapper;
import com.bihell.dice.mapper.auth.AuthRelRoleUserMapper;
import com.bihell.dice.model.auth.*;
import com.bihell.dice.service.auth.AuthRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author haseochen
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthRoleServiceImpl implements AuthRoleService {

    private final AuthRelRoleApiMapper authRelRoleApiMapper;
    private final AuthRelRoleContentMapper authRelRoleContentMapper;
    private final AuthRelRoleUserMapper authRelRoleUserMapper;
    private final AuthItemMapper authItemMapper;

    @Override
    public AuthRole save(AuthRole authRole) {

        if (authRole.selectCount(new QueryWrapper<AuthRole>().lambda().eq(AuthRole::getRoleName, authRole.getRoleName()).eq(AuthRole::getStatus, 1)) > 0) {
            throw new TipException("内容重复！");
        } else {
            authRole.insert();
        }

        return authRole;
    }

    @Override
    public AuthRole update(AuthRole authRole) {
        if (authRole.selectCount(new QueryWrapper<AuthRole>().lambda()
                .eq(AuthRole::getRoleName, authRole.getRoleName())
                .eq(AuthRole::getStatus, 1)
                .ne(AuthRole::getId, authRole.getId())) > 0) {
            throw new TipException("内容重复！");
        } else {
            authRole.updateById();
        }
        return authRole;
    }

    @Override
    public void assignApi(AuthRole authRole) {
        authRelRoleApiMapper.update(null, new UpdateWrapper<AuthRelRoleApi>().lambda()
                .eq(AuthRelRoleApi::getRoleId, authRole.getId())
                .set(AuthRelRoleApi::getStatus, 0));

        if (CollectionUtils.isEmpty(authRole.getApiIds())) {
            return;
        }
        for (Integer apiId : authRole.getApiIds()) {
            if (apiId == null) {
                continue;
            }
            AuthRelRoleApi roleApi = new AuthRelRoleApi();
            roleApi.setRoleId(authRole.getId());
            roleApi.setApiId(apiId);

            roleApi.insert();
        }
    }

    @Override
    public void assignContent(AuthRole authRole) {
        authRelRoleContentMapper.update(null, new UpdateWrapper<AuthRelRoleContent>().lambda()
                .eq(AuthRelRoleContent::getRoleId, authRole.getId())
                .set(AuthRelRoleContent::getStatus, 0));

        if (CollectionUtils.isEmpty(authRole.getContentIds())) {
            return;
        }
        for (Integer contentId : authRole.getContentIds()) {
            if (contentId == null) {
                continue;
            }
            AuthRelRoleContent authRelRoleContent = new AuthRelRoleContent();
            authRelRoleContent.setRoleId(authRole.getId());
            authRelRoleContent.setContentId(contentId);

            authRelRoleContent.insert();
        }
    }

    @Override
    public void assignUser(AuthRole authRole) {
        authRelRoleUserMapper.update(null, new UpdateWrapper<AuthRelRoleUser>().lambda()
                .eq(AuthRelRoleUser::getRoleId, authRole.getId())
                .set(AuthRelRoleUser::getStatus, 0));

        if (CollectionUtils.isEmpty(authRole.getUserIds())) {
            return;
        }
        for (Integer userId : authRole.getContentIds()) {
            if (userId == null) {
                continue;
            }
            AuthRelRoleUser authRelRoleUser = new AuthRelRoleUser();
            authRelRoleUser.setRoleId(authRole.getId());
            authRelRoleUser.setUserId(userId);

            authRelRoleUser.insert();
        }

    }

    /**
     * 只删除、新增当前项目下的item
     */
    @Override
    public void assignItem(AuthRole authRole) {
        List<AuthItem> itemList = authItemMapper.queryByProjectType(authRole.getProjectType());
        List<Integer> itemIds = new ArrayList<>();

        if (itemList != null && itemList.size() != 0) {
            itemIds = itemList.stream().map(AuthItem::getId).collect(Collectors.toList());
        }
        authItemMapper.deleteByRoleId(authRole.getId(), itemIds);

        if (CollectionUtils.isEmpty(authRole.getItemIds())) {
            return;
        }
        for (Integer itemId : authRole.getItemIds()) {
            if (itemId == null) {
                continue;
            }
            AuthRelRoleItem roleItem = new AuthRelRoleItem();
            roleItem.setRoleId(authRole.getId());
            roleItem.setItemId(itemId);

            roleItem.insert();
        }
    }
}
