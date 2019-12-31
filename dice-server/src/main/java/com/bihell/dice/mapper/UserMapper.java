package com.bihell.dice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.model.domain.AuthApi;
import com.bihell.dice.model.domain.User;
import com.bihell.dice.model.params.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User Mapper
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> queryByParam(@Param("pg") Page<User> page, @Param("queryParam") QueryParam queryParam);

}
