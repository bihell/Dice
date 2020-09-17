package com.bihell.dice.blog.service.auth;

import com.bihell.dice.blog.model.auth.AuthGroup;
import com.bihell.dice.blog.model.params.QueryParam;

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
