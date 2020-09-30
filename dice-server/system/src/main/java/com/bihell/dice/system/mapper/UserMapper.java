package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.system.entity.User;
import com.bihell.dice.system.param.QueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * User Mapper
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> queryByParam(@Param("pg") Page<User> page, @Param("queryParam") QueryParam queryParam);

    @Insert("INSERT INTO USER(username, password) VALUES(#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT INTO USER(username, password) VALUES(#{username}, #{password})")
    int insertByObject(User user);
}
