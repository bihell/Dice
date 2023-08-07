package com.bihell.dice.foobar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.foobar.entity.FooBar;
import com.bihell.dice.foobar.req.FooBarRequest;
import com.bihell.dice.foobar.resp.FooBarDetailVo;
import com.bihell.dice.foobar.resp.FooBarResponse;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * FooBar Mapper 接口
 *
 * @author tpxcer
 * @since 2023-07-31
 */
@Mapper
public interface FooBarMapper extends BaseMapper<FooBar> {

    /**
     * FooBar详情
     *
     * @param id
     * @return
     */
    FooBarDetailVo getFooBarById(Long id);

    /**
     * FooBar分页列表
     *
     * @param fooBarRequest
     * @return
     */
    IPage<FooBarResponse> getFooBarList(Page page, FooBarRequest fooBarRequest);

}
