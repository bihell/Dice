package com.bihell.dice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.config.properties.JwtProperties;
import com.bihell.dice.framework.common.exception.TipException;
import com.bihell.dice.framework.common.service.RedisService;
import com.bihell.dice.framework.shiro.cache.LoginRedisService;
import com.bihell.dice.framework.shiro.jwt.JwtToken;
import com.bihell.dice.framework.shiro.util.JwtUtil;
import com.bihell.dice.framework.shiro.util.SaltUtil;
import com.bihell.dice.framework.shiro.vo.LoginSysUserVo;
import com.bihell.dice.framework.util.DiceUtil;
import com.bihell.dice.system.convert.SysUserConvert;
import com.bihell.dice.system.entity.AuthRelRoleUser;
import com.bihell.dice.system.entity.AuthToken;
import com.bihell.dice.system.entity.User;
import com.bihell.dice.system.mapper.AuthRelRoleUserMapper;
import com.bihell.dice.system.mapper.UserMapper;
import com.bihell.dice.system.param.LoginParam;
import com.bihell.dice.system.param.QueryParam;
import com.bihell.dice.system.service.AuthRelRoleUserService;
import com.bihell.dice.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
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

    private final RedisService redisService;
    private final UserMapper userMapper;
    private final AuthRelRoleUserMapper authRelRoleUserMapper;
    private final AuthRelRoleUserService authRelRoleUserService;
    private final JwtProperties jwtProperties;
    private final DiceProperties diceProperties;
    private final LoginRedisService loginRedisService;

    /**
     * Authenticates.
     *
     * @param loginParam login param must not be null
     * @return authentication token
     */
    @NotNull
    @Override
    public AuthToken authenticate(@NotNull LoginParam loginParam) {
        Assert.notNull(loginParam, "Login param must not be null");

        String username = loginParam.getUsername();

        User user = new User().selectOne(new QueryWrapper<User>().lambda()
                .eq(User::getUsername, username)
                .or()
                .eq(User::getEmail, username));
        if (null == user) {
            throw new TipException("用户名或者密码错误");
        }
        String md5 = DiceUtil.getMd5(loginParam.getPassword());
        if (!md5.equals(user.getPasswordMd5())) {
            throw new TipException("用户名或者密码错误");
        }


        // Log it then login successful
        log.info("用户登录: {}", user.getUsername());
        user.setLogged(LocalDateTime.now());
        user.updateById();

        // 将系统用户对象转换成登录用户对象
        LoginSysUserVo loginSysUserVo = SysUserConvert.INSTANCE.sysUserToLoginSysUserVo(user);

        // 获取数据库中保存的盐值
        String newSalt = SaltUtil.getSalt(user.getSalt(), jwtProperties);

        // 生成token字符串并返回
        Long expireSecond = jwtProperties.getExpireSecond();
        String token = JwtUtil.generateToken(username, newSalt, Duration.ofSeconds(expireSecond));
        log.debug("token:{}", token);

        // 创建AuthenticationToken
        JwtToken jwtToken = JwtToken.build(token, username, newSalt, expireSecond);

        boolean enableShiro = diceProperties.getShiro().isEnable();
        if (enableShiro) {
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();
            // 执行认证登录
            subject.login(jwtToken);
        } else {
            log.warn("未启用Shiro");
        }

        // 缓存登录信息到Redis
        loginRedisService.cacheLoginInfo(jwtToken, loginSysUserVo);
        log.debug("登录成功,username:{}", username);

        // todo 后续修改，同时返回用户信息
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken(token);
        return authToken;
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @Override
    public IPage<User> getUserList(QueryParam queryParam) {
        Page<User> page = new Page<>(queryParam.getPageNum(), queryParam.getPageSize());
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .select(User.class, info -> !"passwordMd5".equals(info.getProperty()))
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
                    .map(i-> new AuthRelRoleUser().setUserId(user.getId()).setRoleId(i))
                    .collect(Collectors.toList());
            authRelRoleUserService.saveBatch(authRelRoleUserList);
        }
    }

    @Override
    public void addUser(User user) {
        if (user.selectCount(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername()).or().eq(User::getEmail, user.getEmail())) < 1) {
            user.setPasswordMd5(DiceUtil.getMd5(user.getPasswordMd5()));
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
            user.setPasswordMd5(DiceUtil.getMd5(user.getPasswordMd5()));
            user.updateById();
        } else {
            throw new TipException("用户名或邮箱已存在");
        }
    }

    @Override
    public User getUserSingle(Integer id) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .select(User.class, info -> !"passwordMd5".equals(info.getProperty()))
                .eq(User::getId, id);
        return userMapper.selectOne(wrapper);
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
