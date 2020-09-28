package com.bihell.dice.system.service;


import com.bihell.dice.system.entity.AuthContent;

/**
 * @author haseochen
 */
public interface AuthContentService {

    AuthContent save(AuthContent authContent);

    AuthContent update(AuthContent authContent) ;
}
