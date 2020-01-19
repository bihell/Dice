package com.bihell.dice.service.auth;

import com.bihell.dice.model.auth.AuthGroup;
import com.bihell.dice.model.params.QueryParam;

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
