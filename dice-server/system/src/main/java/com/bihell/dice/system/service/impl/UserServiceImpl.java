package com.bihell.dice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.framework.common.exception.TipException;
import com.bihell.dice.framework.util.DiceUtil;
import com.bihell.dice.system.entity.AuthRelRoleUser;
import com.bihell.dice.system.entity.User;
import com.bihell.dice.system.mapper.UserMapper;
import com.bihell.dice.system.param.QueryParam;
import com.bihell.dice.system.service.AuthRelRoleUserService;
import com.bihell.dice.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User Service 层实现类
 *
 * @author bihell
 * @since 2017/7/12 21:24
 */
@Service("usersService")
@Slf4j
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final AuthRelRoleUserService authRelRoleUserService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public IPage<User> getUserList(QueryParam queryParam) {
        Page<User> page = new Page<>(queryParam.getPageNum(), queryParam.getPageSize());
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .select(User.class, info -> !"password".equals(info.getProperty()))
                .like(User::getUsername, queryParam.getCriteria())
                .orderByDesc(User::getCreated);

        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public void assignRole(User user) {
        user.deleteById();
        if (!CollectionUtils.isEmpty(user.getRoleIds())) {
            List<AuthRelRoleUser> authRelRoleUserList = user.getRoleIds().stream()
                    .filter(Objects::nonNull)
                    .map(i -> new AuthRelRoleUser().setUserId(user.getId()).setRoleId(i))
                    .collect(Collectors.toList());
            authRelRoleUserService.saveBatch(authRelRoleUserList);
        }
    }

    @Override
    public void addUser(User user) {
        if (user.selectCount(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername()).or().eq(User::getEmail, user.getEmail())) < 1) {
            user.setPassword(DiceUtil.getMd5(user.getPassword()));
            user.insert();
        } else {
            throw new TipException("用户名或邮箱已存在");
        }
    }

    @Override
    public void updateUser(User user) {
        if (user.selectCount(new QueryWrapper<User>()
                .ne("id", user.getId())
                .and(field -> {
                    field.eq("username", user.getUsername()).or().eq("email", user.getEmail());
                })) < 1) {
            user.setPassword(DiceUtil.getMd5(user.getPassword()));
            user.updateById();
        } else {
            throw new TipException("用户名或邮箱已存在");
        }
    }

    @Override
    public User getUserSingle(Integer id) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .select(User.class, info -> !"password".equals(info.getProperty()))
                .eq(User::getId, id);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public boolean resetPassword(String username, String oldPassword, String newPassword) {
        User user = new User().selectOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if (null == user) {
            throw new TipException("该用户名不存在");
        }

        if (!user.getPassword().equals(DiceUtil.getMd5(oldPassword))) {
            throw new TipException("原密码错误");
        }

        user.setPassword(DiceUtil.getMd5(newPassword));
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
