package com.bihell.dice.system.service;

import com.bihell.dice.system.entity.AuthItem;

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
