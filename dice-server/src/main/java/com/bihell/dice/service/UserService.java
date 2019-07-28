package com.bihell.dice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bihell.dice.model.domain.User;

/**
 * User Service 接口
 *
 * @author bihell
 * @since 2017/7/12 21:25
 */
public interface UserService extends IService<User> {

    /**
     * 用户登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User login(String username, String password);

    /**
     * 修改用户密码
     *
     * @param username    用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return boolean
     */
    boolean resetPassword(String username, String oldPassword, String newPassword);

    /**
     * 修改用户信息
     *
     * @param oldUsername 原用户名
     * @param newUsername 新用户名
     * @param email       邮箱
     * @return boolean
     */
    boolean resetUser(String oldUsername, String newUsername, String email);
}
