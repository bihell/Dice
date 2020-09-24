package com.bihell.dice.framework.shiro.service;

import java.io.Serializable;

/**
 * 获取登录token todo
 **/
public interface LoginToken extends Serializable {

    /**
     * 获取登录token
     *
     * @return
     */
    String getToken();

}
