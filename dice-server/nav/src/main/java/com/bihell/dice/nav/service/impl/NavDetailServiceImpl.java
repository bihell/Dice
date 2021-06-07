package com.bihell.dice.nav.service.impl;

import com.bihell.dice.nav.entity.NavDetail;
import com.bihell.dice.nav.mapper.NavDetailMapper;
import com.bihell.dice.nav.param.NavDetailPageParam;
import com.bihell.dice.nav.service.NavDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bihell.dice.framework.common.service.impl.BaseServiceImpl;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.core.pagination.PageInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 导航明细表 服务实现类
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Slf4j
@Service
public class NavDetailServiceImpl extends BaseServiceImpl<NavDetailMapper, NavDetail> implements NavDetailService {

    @Autowired
    private NavDetailMapper navDetailMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveNavDetail(NavDetail navDetail) throws Exception {
        return super.save(navDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateNavDetail(NavDetail navDetail) throws Exception {
        return super.updateById(navDetail);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteNavDetail(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<NavDetail> getNavDetailPageList(NavDetailPageParam navDetailPageParam) throws Exception {
        Page<NavDetail> page = new PageInfo<>(navDetailPageParam, OrderItem.desc(getLambdaColumn(NavDetail::getCreateTime)));
        LambdaQueryWrapper<NavDetail> wrapper = new LambdaQueryWrapper<>();
        IPage<NavDetail> iPage = navDetailMapper.selectPage(page, wrapper);
        return new Paging<NavDetail>(iPage);
    }

}
