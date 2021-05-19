package com.bihell.dice.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.framework.common.exception.BusinessException;
import com.bihell.dice.framework.common.exception.TipException;
import com.bihell.dice.framework.core.pagination.PageInfo;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.shiro.util.SaltUtil;
import com.bihell.dice.framework.util.PasswordUtil;
import com.bihell.dice.system.entity.AuthRelRoleUser;
import com.bihell.dice.system.entity.User;
import com.bihell.dice.system.mapper.UserMapper;
import com.bihell.dice.system.param.UserPageParam;
import com.bihell.dice.system.service.AuthRelRoleUserService;
import com.bihell.dice.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.bihell.dice.framework.common.service.impl.BaseServiceImpl;

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
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final AuthRelRoleUserService authRelRoleUserService;
    private final DiceProperties diceProperties;

    @Override
    public Paging<User> getUserPageList(UserPageParam userPageParam) {
        Page<User> page =  new PageInfo<>(userPageParam, OrderItem.desc(getLambdaColumn(User::getLogged)));
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .select(User.class, info -> !"password".equals(info.getProperty()))
                .like(!StringUtils.isEmpty(userPageParam.getCriteria()),User::getUsername, userPageParam.getCriteria());
        IPage<User> iPage = userMapper.selectPage(page, wrapper);
        return new Paging(iPage);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(User user) {
        // 校验用户名是否存在
        boolean isExists = user.selectCount(new QueryWrapper<User>().lambda().eq(User::getUsername, user.getUsername()).or().eq(User::getEmail, user.getEmail())) > 0;
        if (isExists) {
            throw new BusinessException("用户名或邮箱已存在");
        }
        // 校验部门和角色 todo
//        checkDepartmentAndRole(sysUser.getDepartmentId(), sysUser.getRoleId());
//        sysUser.setId(null);

        // 生成盐值
        String salt = null;
        String password = user.getPassword();
        // 如果密码为空，则设置默认密码
        if (StringUtils.isBlank(password)) {
            salt = diceProperties.getLoginInitSalt();
            password = diceProperties.getLoginInitPassword();
        } else {
            salt = SaltUtil.generateSalt();
        }
        // 密码加密
        user.setSalt(salt);
        user.setPassword(PasswordUtil.encrypt(password, salt));

        // 如果头像为空，则设置默认头像 todo
//        if (StringUtils.isBlank(sysUser.getHead())) {
//            sysUser.setHead(springBootPlusProperties.getLoginInitHead());
//        }

        // 保存系统用户
        return user.insert();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user) {
        if (user.selectCount(new QueryWrapper<User>()
                .ne("id", user.getId())
                .and(field -> {
                    field.eq("username", user.getUsername()).or().eq("email", user.getEmail());
                })) < 1) {
            String salt = SaltUtil.generateSalt();
            user.setSalt(salt);
            String password = user.getPassword();
            user.setPassword(PasswordUtil.encrypt(password, salt));
            return user.updateById();
        } else {
            throw new BusinessException("用户名或邮箱已存在");
        }
    }

    @Override
    public User getUserSingle(Integer id) {
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .select(User.class, info -> !"password".equals(info.getProperty()))
                .eq(User::getId, id);
        return userMapper.selectOne(wrapper);
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
