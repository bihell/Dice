package com.bihell.dice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.commons.exception.BusinessException;
import com.bihell.dice.commons.service.impl.BaseServiceImpl;
import com.bihell.dice.commons.pagination.PageInfo;
import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.system.convert.SysPermissionConvert;
import com.bihell.dice.system.entity.SysMenu;
import com.bihell.dice.system.enums.MenuLevelEnum;
import com.bihell.dice.system.enums.StateEnum;
import com.bihell.dice.system.mapper.SysMenuMapper;
import com.bihell.dice.system.param.SysPermissionPageParam;
import com.bihell.dice.system.service.SysMenuService;
import com.bihell.dice.system.service.SysRoleMenuService;
import com.bihell.dice.system.vo.SysPermissionVo;
import com.bihell.dice.system.vo.SysPermissionTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <pre>
 * 系统权限 服务实现类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-25
 */
@Slf4j
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSysPermission(SysMenu sysMenu) throws Exception {
        sysMenu.setId(null);
        return super.save(sysMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysPermission(SysMenu sysMenu) throws Exception {
        // 获取权限
        if (getById(sysMenu.getId()) == null) {
            throw new BusinessException("权限不存在");
        }
//        sysPermission.setUpdateTime(new Date());
        return super.updateById(sysMenu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysPermission(Long id) throws Exception {
        boolean isExists = sysRoleMenuService.isExistsByPermissionId(id);
        if (isExists) {
            throw new BusinessException("该权限存在角色关联关系，不能删除");
        }
        return super.removeById(id);
    }

    @Override
    public SysPermissionVo getSysPermissionById(Serializable id) throws Exception {
        return sysMenuMapper.getSysPermissionById(id);
    }

    @Override
    public Paging<SysPermissionVo> getSysPermissionPageList(SysPermissionPageParam sysPermissionPageParam) throws Exception {
        Page<SysPermissionVo> page = new PageInfo<>(sysPermissionPageParam, OrderItem.desc("create_time"));
        IPage<SysPermissionVo> iPage = sysMenuMapper.getSysPermissionPageList(page, sysPermissionPageParam);
        return new Paging(iPage);
    }

    @Override
    public boolean isExistsByPermissionIds(List<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return false;
        }
        Wrapper wrapper = lambdaQuery().in(SysMenu::getId, permissionIds).getWrapper();
        return sysMenuMapper.selectCount(wrapper).intValue() == permissionIds.size();
    }

    @Override
    public List<SysMenu> getAllMenuList() throws Exception {
        SysMenu sysMenu = new SysMenu().setStatus(StateEnum.ENABLE.getCode());
        // 获取所有已启用的权限列表
        return sysMenuMapper.selectList(new QueryWrapper(sysMenu));
    }

    @Override
    public List<SysPermissionTreeVo> getAllMenuTree() throws Exception {
        List<SysMenu> list = getAllMenuList();
        // 转换成树形菜单
        List<SysPermissionTreeVo> treeVos = convertSysPermissionTreeVoList(list);
        return treeVos;
    }

    @Override
    public List<SysPermissionTreeVo> convertSysPermissionTreeVoList(List<SysMenu> list) {
         if (CollectionUtils.isEmpty(list)) {
             throw new IllegalArgumentException("SysPermission列表不能为空");
         }
        // 按level分组获取map
        Map<Integer, List<SysMenu>> map = list.stream().collect(Collectors.groupingBy(SysMenu::getLevel));
        List<SysPermissionTreeVo> treeVos = new ArrayList<>();
        // 循环获取三级菜单树形集合
        for (SysMenu one : map.get(MenuLevelEnum.ONE.getCode())) {
            SysPermissionTreeVo oneVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(one);
            Long oneParentId = oneVo.getParentId();
            if (oneParentId == null || oneParentId == 0) {
                treeVos.add(oneVo);
            }
            List<SysMenu> twoList = map.get(MenuLevelEnum.TWO.getCode());
            if (CollectionUtils.isNotEmpty(twoList)) {
                for (SysMenu two : twoList) {
                    SysPermissionTreeVo twoVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(two);
                    if (two.getParentId().equals(one.getId())) {
                        if (oneVo.getChildren() == null) {
                            oneVo.setChildren(new ArrayList<>());
                        }
                        oneVo.getChildren().add(twoVo);
                    }
                    List<SysMenu> threeList = map.get(MenuLevelEnum.THREE.getCode());
                    if (CollectionUtils.isNotEmpty(threeList)) {
                        for (SysMenu three : threeList) {
                            if (three.getParentId().equals(two.getId())) {
                                SysPermissionTreeVo threeVo = SysPermissionConvert.INSTANCE.permissionToTreeVo(three);
                                if (twoVo.getChildren() == null) {
                                    twoVo.setChildren(new ArrayList<>());
                                }
                                twoVo.getChildren().add(threeVo);
                            }
                        }
                    }
                }
            }

        }
        return treeVos;
    }

    @Override
    public Set<String> getPermissionCodesByUserId(Long userId)  {
        return sysMenuMapper.getPermissionCodesByUserId(userId);
    }

    @Override
    public List<SysMenu> getMenuListByUserId(Long userId) throws Exception {
        return sysMenuMapper.getMenuListByUserId(userId);
    }

    @Override
    public List<SysPermissionTreeVo> getMenuTreeByUserId(Long userId) throws Exception {
        List<SysMenu> list = getMenuListByUserId(userId);
        // 转换成树形菜单
        List<SysPermissionTreeVo> treeVos = convertSysPermissionTreeVoList(list);
        return treeVos;
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception {

        // 根据角色id获取该对应的所有三级权限ID

        return null;
    }


    @Override
    public List<SysPermissionTreeVo> getNavMenuTree() throws Exception {
        List<Integer> levels = Arrays.asList(MenuLevelEnum.ONE.getCode(), MenuLevelEnum.TWO.getCode());
        Wrapper wrapper = lambdaQuery()
                .in(SysMenu::getLevel, levels)
                .eq(SysMenu::getStatus, StateEnum.ENABLE.getCode())
                .getWrapper();


        List<SysMenu> list = sysMenuMapper.selectList(wrapper);

        return convertSysPermissionTreeVoList(list);

    }
}
