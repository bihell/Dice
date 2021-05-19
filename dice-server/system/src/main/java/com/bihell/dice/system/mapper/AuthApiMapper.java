package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.system.entity.AuthApi;
import com.bihell.dice.system.param.ApiPageParam;
import org.apache.ibatis.annotations.Param;

/**
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface AuthApiMapper extends BaseMapper<AuthApi> {

    /**
     * 获取 Api 列表
     * @param page
     * @param apiPageParam
     * @return
     */
    IPage<AuthApi> queryByParam(@Param("pg") Page<AuthApi> page, @Param("queryParam") ApiPageParam apiPageParam);

}
