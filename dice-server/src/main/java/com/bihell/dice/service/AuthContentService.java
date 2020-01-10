package com.bihell.dice.service;

import com.bihell.dice.model.domain.AuthContent;

/**
 * @author haseochen
 */
public interface AuthContentService {

    AuthContent save(AuthContent authContent);

    AuthContent update(AuthContent authContent) ;
}
