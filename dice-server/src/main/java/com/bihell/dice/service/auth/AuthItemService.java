package com.bihell.dice.service.auth;

import com.bihell.dice.model.domain.AuthItem;

/**
 * @author haseochen
 */
public interface AuthItemService {

    /**
     * @param authItem
     * @return
     */
    AuthItem save(AuthItem authItem);

    AuthItem update(AuthItem authItem) ;

    void assignApi(AuthItem authItem);
}
