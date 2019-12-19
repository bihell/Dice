package com.bihell.dice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bihell.dice.model.domain.AuthClasses;
import java.util.List;

/**
 *
 * @author bihell
 * @since 2017/7/12 21:34
 */
public interface AuthClassesMapper extends BaseMapper<AuthClasses> {

    /**
     * 通过 GroupId 获取菜单项
     * @param groupId
     * @return
     */
    List<AuthClasses> queryByGroupId(Integer groupId);

}
