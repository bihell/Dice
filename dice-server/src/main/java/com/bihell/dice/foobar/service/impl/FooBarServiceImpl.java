package com.bihell.dice.foobar.service.impl;

import com.bihell.dice.commons.exception.BusinessException;
import com.bihell.dice.commons.pagination.OrderByItem;
import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.commons.service.impl.BaseServiceImpl;
import com.bihell.dice.foobar.dto.FooBarAddDTO;
import com.bihell.dice.foobar.dto.FooBarUpdateDTO;
import com.bihell.dice.foobar.entity.FooBar;
import com.bihell.dice.foobar.mapper.FooBarMapper;
import com.bihell.dice.foobar.req.FooBarRequest;
import com.bihell.dice.foobar.service.FooBarService;
import com.bihell.dice.foobar.resp.FooBarDetailVo;
import com.bihell.dice.foobar.resp.FooBarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.commons.pagination.PageInfo;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

import java.util.Date;
import java.util.List;

/**
 * FooBar 服务实现类
 *
 * @author tpxcer
 * @since 2023-07-31
 */
@Slf4j
@Service
public class FooBarServiceImpl extends BaseServiceImpl<FooBarMapper, FooBar> implements FooBarService {

    @Autowired
    private FooBarMapper fooBarMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addFooBar(FooBarAddDTO fooBarAddDTO) throws Exception {
        FooBar fooBar = new FooBar();
        BeanUtils.copyProperties(fooBarAddDTO, fooBar);
        return save(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateFooBar(FooBarUpdateDTO fooBarUpdateDTO) throws Exception {
        Long id = fooBarUpdateDTO.getId();
        FooBar fooBar = getById(id);
        if (fooBar == null) {
            throw new BusinessException("FooBar不存在");
        }
        BeanUtils.copyProperties(fooBarUpdateDTO, fooBar);
        fooBar.setUpdateTime(new Date());
        return updateById(fooBar);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteFooBar(Long id) throws Exception {
        return removeById(id);
    }

    @Override
    public FooBarDetailVo getFooBarById(Long id) throws Exception {
        return fooBarMapper.getFooBarById(id);
    }

    @Override
    public Paging<FooBarResponse> getFooBarList(FooBarRequest fooBarRequest) throws Exception {
        Page<FooBarResponse> page = new PageInfo<>(fooBarRequest, OrderItem.desc("id"));
        IPage<FooBarResponse> iPage = fooBarMapper.getFooBarList(page,fooBarRequest);
        return new Paging(iPage);
    }

}
