package com.bihell.dice.nav.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.nav.convert.NavTypeConvert;
import com.bihell.dice.nav.entity.NavType;
import com.bihell.dice.nav.mapper.NavTypeMapper;
import com.bihell.dice.nav.param.NavTypePageParam;
import com.bihell.dice.nav.service.NavTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bihell.dice.framework.common.service.impl.BaseServiceImpl;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.core.pagination.PageInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.nav.vo.NavTypeVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务实现类
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Slf4j
@Service
public class NavTypeServiceImpl extends BaseServiceImpl<NavTypeMapper, NavType> implements NavTypeService {

    @Autowired
    private NavTypeMapper navTypeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveNavType(NavType navDetail) throws Exception {
        return super.save(navDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateNavType(NavType navDetail) throws Exception {
        return super.updateById(navDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteNavType(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<NavType> getNavTypePageList(NavTypePageParam navDetailPageParam) throws Exception {
        Page<NavType> page = new PageInfo<>(navDetailPageParam, OrderItem.desc(getLambdaColumn(NavType::getCreateTime)));
        LambdaQueryWrapper<NavType> wrapper = new LambdaQueryWrapper<>();
        IPage<NavType> iPage = navTypeMapper.selectPage(page, wrapper);
        return new Paging<NavType>(iPage);
    }

    @Override
    public List<NavType> getAllNavTypeList() {
        return navTypeMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<NavTypeVo> getNavTypeTree() {
        List<NavType> navTypeList = getAllNavTypeList();
        if (CollectionUtils.isEmpty(navTypeList)) {
            throw new IllegalArgumentException("NavType列表不能为空");
        }
        List<NavTypeVo> list = NavTypeConvert.INSTANCE.listToTreeVoList(navTypeList);
        System.out.println(list);
        List<NavTypeVo> treeVos = new ArrayList<>();
        for (NavTypeVo treeVo : list) {
            if (treeVo.getParentId() == null) {
                treeVos.add(findChildren(treeVo, list));
            }
        }
        return treeVos;
    }

    /**
     * 递归获取树形结果列表
     *
     * @param tree
     * @param list
     * @return
     */
    public NavTypeVo findChildren(NavTypeVo tree, List<NavTypeVo> list) {
        for (NavTypeVo vo : list) {
            if (tree.getId().equals(vo.getParentId())) {
                if (tree.getChildren() == null) {
                    tree.setChildren(new ArrayList<>());
                }
                tree.getChildren().add(findChildren(vo, list));
            }
        }
        return tree;
    }

}
