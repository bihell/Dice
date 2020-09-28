package com.bihell.dice.system.service;

import com.bihell.dice.system.entity.AuthGroup;
import com.bihell.dice.system.param.QueryParam;

import java.util.List;

/**
 * @author haseochen
 */
public interface AuthGroupService {

    /**
     * @param param 查询参数
     * @return List<AuthGroup>
     */
    List<AuthGroup> getGroupList(QueryParam param);
}
