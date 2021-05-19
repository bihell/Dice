package com.bihell.dice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.framework.common.exception.TipException;
import com.bihell.dice.framework.common.service.impl.BaseServiceImpl;
import com.bihell.dice.framework.core.pagination.PageInfo;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.system.entity.*;
import com.bihell.dice.system.mapper.AuthItemMapper;
import com.bihell.dice.system.mapper.AuthRoleMapper;
import com.bihell.dice.system.param.RolePageParam;
import com.bihell.dice.system.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author haseochen
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthRoleServiceImpl extends BaseServiceImpl<AuthRoleMapper,AuthRole> implements AuthRoleService {
    private final AuthItemMapper authItemMapper;
    private final AuthRelRoleApiService authRelRoleApiService;
    private final AuthRelRoleContentService authRelRoleContentService;
    private final AuthRelRoleUserService authRelRoleUserService;
    private final AuthRelRoleItemService authRelRoleItemService;
    private final AuthRoleMapper authRoleMapper;

    @Override
    public AuthRole saveRole(AuthRole authRole) {

        if (authRole.selectCount(new QueryWrapper<AuthRole>().lambda()
                .eq(AuthRole::getRoleName, authRole.getRoleName())
        ) > 0) {
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
                .ne(AuthRole::getId, authRole.getId())) > 0) {
            throw new TipException("内容重复！");
        } else {
            authRole.updateById();
        }
        return authRole;
    }

    @Override
    public void assignApi(AuthRole authRole) {
        authRole.deleteById();

        if (!CollectionUtils.isEmpty(authRole.getApiIds())) {
            List<AuthRelRoleApi> authRelRoleApiList = authRole.getApiIds().stream()
                    .filter(Objects::nonNull)
                    .map(i -> new AuthRelRoleApi().setRoleId(authRole.getId()).setApiId(i))
                    .collect(Collectors.toList());
            authRelRoleApiService.saveBatch(authRelRoleApiList);
        }
    }

    @Override
    public void assignContent(AuthRole authRole) {
        authRole.deleteById();

        if (!CollectionUtils.isEmpty(authRole.getContentIds())) {
            List<AuthRelRoleContent> authRelRoleContentList = authRole.getContentIds().stream()
                    .filter(Objects::nonNull)
                    .map(i->new AuthRelRoleContent().setRoleId(authRole.getId()).setContentId(i))
                    .collect(Collectors.toList());
            authRelRoleContentService.saveBatch(authRelRoleContentList);
        }
    }

    @Override
    public void assignUser(AuthRole authRole) {
        authRole.deleteById();

        if (!CollectionUtils.isEmpty(authRole.getUserIds())) {
            List<AuthRelRoleUser> authRelRoleUserList = authRole.getUserIds().stream()
                    .filter(Objects::nonNull)
                    .map(i->new AuthRelRoleUser().setRoleId(authRole.getId()).setUserId(Long.valueOf(i)))
                    .collect(Collectors.toList());
            authRelRoleUserService.saveBatch(authRelRoleUserList);
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

        if (!CollectionUtils.isEmpty(authRole.getItemIds())) {
            List<AuthRelRoleItem> authRelRoleItemList = authRole.getItemIds().stream()
                    .filter(Objects::nonNull)
                    .map(i-> new AuthRelRoleItem().setRoleId(authRole.getId()).setItemId(i))
                    .collect(Collectors.toList());
            authRelRoleItemService.saveBatch(authRelRoleItemList);
        }
    }

    @Override
    public Paging<AuthRole> getRolePageList(RolePageParam rolePageParam) {
        Page<AuthRole> page = new PageInfo<>(rolePageParam, OrderItem.desc(getLambdaColumn(AuthRole::getUpdateTime)));
        IPage<AuthRole> iPage = authRoleMapper.queryByParam(page,rolePageParam);
        return new Paging(iPage);
    }
}
