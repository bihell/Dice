package com.bihell.dice.blog.service.auth;

import com.bihell.dice.blog.model.auth.AuthItem;

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
