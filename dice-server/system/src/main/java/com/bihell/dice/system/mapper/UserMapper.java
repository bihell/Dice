package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.system.entity.SysUser;
import com.bihell.dice.system.param.QueryParam;
import com.bihell.dice.system.param.UserPageParam;
import com.bihell.dice.system.vo.SysUserQueryVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * User Mapper
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface UserMapper extends BaseMapper<SysUser> {

    IPage<SysUser> queryByParam(@Param("pg") Page<SysUser> page, @Param("queryParam") QueryParam queryParam);


    /**
     * 获取分页对象
     *
     * @param page
     * @param sysUserPageParam
     * @return
     */
    IPage<SysUserQueryVo> getSysUserPageList(@Param("page") Page page, @Param("param") UserPageParam sysUserPageParam);

    @Insert("INSERT INTO USER(username, password) VALUES(#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT INTO USER(username, password) VALUES(#{username}, #{password})")
    int insertByObject(SysUser sysUser);
}
