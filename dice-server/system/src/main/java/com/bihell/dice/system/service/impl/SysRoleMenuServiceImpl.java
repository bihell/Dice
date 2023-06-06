package com.bihell.dice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.framework.common.service.impl.BaseServiceImpl;
import com.bihell.dice.system.entity.SysRoleMenu;
import com.bihell.dice.system.entity.SysUserRole;
import com.bihell.dice.system.enums.StateEnum;
import com.bihell.dice.system.mapper.SysRoleMapper;
import com.bihell.dice.system.mapper.SysRoleMenuMapper;
import com.bihell.dice.system.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * <pre>
 * 角色权限关系 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-25
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysRolePermission(Long roleId, List<Long> permissionIds) throws Exception {
        List<SysRoleMenu> list = new ArrayList<>();
        permissionIds.forEach(permissionId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu()
                    .setRoleId(roleId)
                    .setPermissionId(permissionId)
                    .setStatus(StateEnum.ENABLE.getCode());
            list.add(sysRoleMenu);
        });
        // 批量保存角色权限中间表
        return saveBatch(list, 20);
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception {
        Wrapper wrapper = lambdaQuery()
                .select(SysRoleMenu::getPermissionId)
                .eq(SysRoleMenu::getRoleId,roleId)
                .eq(SysRoleMenu::getStatus,StateEnum.ENABLE.getCode())
                .getWrapper();
        return sysRoleMenuMapper.selectObjs(wrapper);
    }

    @Override
    public List<Long> getThreeLevelPermissionIdsByRoleId(Long roleId) throws Exception {
        return sysRoleMenuMapper.getThreeLevelPermissionIdsByRoleId(roleId);
    }

    @Override
    public boolean saveSysRolePermissionBatch(Long roleId, SetUtils.SetView addSet) {
        List<SysRoleMenu> list = new ArrayList<>();
        addSet.forEach(id -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            Long permissionId = (Long) id;
            sysRoleMenu
                    .setRoleId(roleId)
                    .setPermissionId(permissionId)
                    .setStatus(StateEnum.ENABLE.getCode());
            list.add(sysRoleMenu);
        });
        return saveBatch(list, 20);
    }

    @Override
    public boolean deleteSysRolePermissionByRoleId(Long roleId) throws Exception {
        SysRoleMenu sysRoleMenu = new SysRoleMenu()
                .setRoleId(roleId);
        return remove(new QueryWrapper<>(sysRoleMenu));
    }

    @Override
    public Set<String> getPermissionCodesByRoleId(List<SysUserRole> sysUserRoleList) throws Exception {
        List<Long> roleIds = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        return sysRoleMenuMapper.getPermissionCodesByRoleId(roleIds);
    }

    @Override
    public boolean isExistsByPermissionId(Long permissionId) throws Exception {
        // 判断角色权限表是否有关联存在，如果存在，则不能删除
        SysRoleMenu sysRoleMenu = new SysRoleMenu()
                .setPermissionId(permissionId);
        return count(new QueryWrapper<SysRoleMenu>(sysRoleMenu)) > 0;
    }

    @Override
    public boolean hasPermission(Long roleId) throws Exception {
        SysRoleMenu sysRoleMenu = new SysRoleMenu()
                .setRoleId(roleId);
        return count(new QueryWrapper<SysRoleMenu>(sysRoleMenu)) > 0;
    }

}
