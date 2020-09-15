package com.bihell.dice.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.model.auth.AuthContent;
import com.bihell.dice.model.params.QueryParam;
import org.apache.ibatis.annotations.Param;


/**
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface AuthContentMapper extends BaseMapper<AuthContent> {


    /**
     * 查询 role 列表
     * @param page
     * @param queryParam
     * @return
     */
    IPage<AuthContent> queryByParam(@Param("pg") Page<AuthContent> page, @Param("queryParam") QueryParam queryParam);

}
