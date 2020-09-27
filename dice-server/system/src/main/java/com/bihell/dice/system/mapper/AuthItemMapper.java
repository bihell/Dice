package com.bihell.dice.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.system.entity.AuthItem;
import com.bihell.dice.system.param.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface AuthItemMapper extends BaseMapper<AuthItem> {

    /**
     * @param param
     * @return
     */
    List<AuthItem> queryByParam(QueryParam param);

    List<AuthItem> queryByProjectType(String projectType);

    void deleteByRoleId(@Param("roleId") Integer roleId, @Param("itemIds") List<Integer> itemIds);
}
