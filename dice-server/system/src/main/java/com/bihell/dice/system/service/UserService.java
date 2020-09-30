package com.bihell.dice.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bihell.dice.system.entity.User;
import com.bihell.dice.system.param.QueryParam;

/**
 * User Service 接口
 *
 * @author bihell
 * @since 2017/7/12 21:25
 */
public interface UserService extends IService<User> {

    boolean addUser(User user);

    boolean updateUser(User user);

    User getUserSingle(Integer id);

    /**
     * 修改用户信息
     *
     * @param oldUsername 原用户名
     * @param newUsername 新用户名
     * @param email       邮箱
     * @return boolean
     */
    boolean resetUser(String oldUsername, String newUsername, String email);

    /**
     * 获取用户列表
     * @return
     */
    IPage<User> getUserList(QueryParam queryParam);

    void assignRole(User user);
}
