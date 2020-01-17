package com.bihell.dice.service.auth;

import com.bihell.dice.model.auth.AuthContent;

/**
 * @author haseochen
 */
public interface AuthContentService {

    AuthContent save(AuthContent authContent);

    AuthContent update(AuthContent authContent) ;
}
