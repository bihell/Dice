package com.bihell.dice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.mapper.UserMapper;
import com.bihell.dice.model.domain.User;
import com.bihell.dice.service.UserService;
import com.bihell.dice.util.DiceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * User Service 层实现类
 *
 * @author bihell
 * @since 2017/7/12 21:24
 */
@Service("usersService")
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(String username, String password) {

        User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username).or().eq(User::getEmail, username));
        if (null == user) {
            throw new TipException("用户名或者密码错误");
        }
        String md5 = DiceUtil.getMd5(password);
        if (!md5.equals(user.getPasswordMd5())) {
            throw new TipException("用户名或者密码错误");
        }
        user.setLogged(new Date());
        user.updateById();

        //清空密码
        user.setPasswordMd5(null);
        return user;

    }

    @Override
    public boolean resetPassword(String username, String oldPassword, String newPassword) {
        User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if (null == user) {
            throw new TipException("该用户名不存在");
        }

        if (!user.getPasswordMd5().equals(DiceUtil.getMd5(oldPassword))) {
            throw new TipException("原密码错误");
        }

        user.setPasswordMd5(DiceUtil.getMd5(newPassword));
        return user.updateById();
    }

    /**
     * 修改用户信息
     *
     * @param oldUsername 原用户名
     * @param newUsername 新用户名
     * @param email       邮箱
     * @return boolean
     */
    @Override
    public boolean resetUser(String oldUsername, String newUsername, String email) {
        User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, oldUsername));
        if (null == user) {
            throw new TipException("该用户名不存在");
        }

        user.setUsername(newUsername);
        user.setEmail(email);

        return user.updateById();
    }
}
