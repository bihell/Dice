package com.bihell.dice.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.model.auth.AuthRole;
import com.bihell.dice.model.params.QueryParam;
import org.apache.ibatis.annotations.Param;


/**
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface AuthRoleMapper extends BaseMapper<AuthRole> {


    /**
     * 查询 role 列表
     * @param page
     * @param queryParam
     * @return
     */
    IPage<AuthRole> queryByParam(@Param("pg") Page<AuthRole> page, @Param("queryParam") QueryParam queryParam);

}
