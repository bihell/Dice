package com.bihell.dice.framework.shiro.service;

import java.io.Serializable;

/**
 * 获取登录用户名称
 *
 * @author haseochen*/
public interface LoginUsername extends Serializable {

    /**
     * 获取用户名
     *
     * @return
     */
    String getUsername();

}
