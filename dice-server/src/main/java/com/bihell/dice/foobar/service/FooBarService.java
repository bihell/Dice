package com.bihell.dice.foobar.service;

import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.commons.service.BaseService;
import com.bihell.dice.foobar.dto.FooBarAddDTO;
import com.bihell.dice.foobar.dto.FooBarUpdateDTO;
import com.bihell.dice.foobar.entity.FooBar;
import com.bihell.dice.foobar.req.FooBarRequest;
import com.bihell.dice.foobar.resp.FooBarDetailVo;
import com.bihell.dice.foobar.resp.FooBarResponse;

/**
 * FooBar 服务接口
 *
 * @author tpxcer
 * @since 2023-07-31
 */
public interface FooBarService extends BaseService<FooBar> {

    /**
     * 添加FooBar
     *
     * @param fooBarAddDTO
     * @return
     * @throws Exception
     */
    boolean addFooBar(FooBarAddDTO fooBarAddDTO) throws Exception;

    /**
     * 修改FooBar
     *
     * @param fooBarUpdateDTO
     * @return
     * @throws Exception
     */
    boolean updateFooBar(FooBarUpdateDTO fooBarUpdateDTO) throws Exception;

    /**
     * 删除FooBar
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteFooBar(Long id) throws Exception;

    /**
     * FooBar详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    FooBarDetailVo getFooBarById(Long id) throws Exception;

    /**
     * FooBar分页列表
     *
     * @param fooBarRequest
     * @return
     * @throws Exception
     */
    Paging<FooBarResponse> getFooBarList(FooBarRequest fooBarRequest) throws Exception;

}
