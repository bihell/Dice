package com.bihell.dice.system.service;

import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.system.entity.AuthRole;
import com.bihell.dice.system.param.RolePageParam;

/**
 * @author haseochen
 */
public interface AuthRoleService {

    AuthRole saveRole(AuthRole authRole);

    AuthRole update(AuthRole authRole) ;

    void assignApi (AuthRole authRole);

    void assignContent(AuthRole authRole);

    void assignUser (AuthRole authRole);

    void assignItem (AuthRole authRole);

    /**
     * 获取角色列表
     * @param rolePageParam
     * @return
     */
    Paging<AuthRole> getRolePageList(RolePageParam rolePageParam);
}
