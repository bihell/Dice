package com.bihell.dice.system.service;

import com.bihell.dice.system.entity.AuthRole;

/**
 * @author haseochen
 */
public interface AuthRoleService {

    AuthRole save(AuthRole authRole);

    AuthRole update(AuthRole authRole) ;

    void assignApi (AuthRole authRole);

    void assignContent(AuthRole authRole);

    void assignUser (AuthRole authRole);

    void assignItem (AuthRole authRole);
}
