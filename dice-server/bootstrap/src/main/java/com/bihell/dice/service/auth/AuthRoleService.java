package com.bihell.dice.service.auth;

import com.bihell.dice.model.auth.AuthRole;

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
