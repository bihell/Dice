package com.bihell.dice.blog.service.auth;

import com.bihell.dice.blog.model.auth.AuthContent;

/**
 * @author haseochen
 */
public interface AuthContentService {

    AuthContent save(AuthContent authContent);

    AuthContent update(AuthContent authContent) ;
}
