package com.bihell.dice.mapper.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.model.auth.AuthItem;
import com.bihell.dice.model.params.QueryParam;
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
