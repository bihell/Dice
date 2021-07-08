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
import com.bihell.dice.framework.util.PhoneUtil;
import com.bihell.dice.system.entity.AuthRelRoleUser;
import com.bihell.dice.system.entity.SysUser;
import com.bihell.dice.system.mapper.UserMapper;
import com.bihell.dice.system.param.UserPageParam;
import com.bihell.dice.system.service.AuthRelRoleUserService;
import com.bihell.dice.system.service.SysDepartmentService;
import com.bihell.dice.system.service.SysRoleService;
import com.bihell.dice.system.service.UserService;
import com.bihell.dice.system.vo.SysUserQueryVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.bihell.dice.framework.common.service.impl.BaseServiceImpl;

import java.util.Date;
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
public class UserServiceImpl extends BaseServiceImpl<UserMapper, SysUser> implements UserService {

    private final UserMapper userMapper;
    private final AuthRelRoleUserService authRelRoleUserService;
    private final DiceProperties diceProperties;

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @Lazy
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public Paging<SysUserQueryVo> getUserPageList(UserPageParam userPageParam) {
        Page<SysUserQueryVo> page = new PageInfo<>(userPageParam, OrderItem.desc(getLambdaColumn(SysUser::getCreateTime)));
        IPage<SysUserQueryVo> iPage = userMapper.getSysUserPageList(page, userPageParam);

        // 手机号码脱敏处理
        if (iPage != null && org.apache.commons.collections4.CollectionUtils.isNotEmpty(iPage.getRecords())) {
            iPage.getRecords().forEach(vo -> {
                vo.setPhone(PhoneUtil.desensitize(vo.getPhone()));
            });
        }

        return new Paging(iPage);
    }

    @Override
    public void assignRole(SysUser sysUser) {
        sysUser.deleteById();
        if (!CollectionUtils.isEmpty(sysUser.getRoles())) {
            List<AuthRelRoleUser> authRelRoleUserList = sysUser.getRoles().stream()
                    .filter(Objects::nonNull)
                    .map(i -> new AuthRelRoleUser().setUserId(sysUser.getId()).setRoleId(i))
                    .collect(Collectors.toList());
            authRelRoleUserService.saveBatch(authRelRoleUserList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(SysUser sysUser) {
        // 校验用户名是否存在
        boolean isExists = sysUser.selectCount(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, sysUser.getUsername()).or().eq(SysUser::getEmail, sysUser.getEmail())) > 0;
        if (isExists) {
            throw new BusinessException("用户名或邮箱已存在");
        }
        // 校验部门和角色 todo
//        checkDepartmentAndRole(sysUser.getDepartmentId(), sysUser.getRoleId());
//        sysUser.setId(null);

        // 生成盐值
        String salt = null;
        String password = sysUser.getPwd();
        // 如果密码为空，则设置默认密码
        if (StringUtils.isBlank(password)) {
            salt = diceProperties.getLoginInitSalt();
            password = diceProperties.getLoginInitPassword();
        } else {
            salt = SaltUtil.generateSalt();
        }
        // 密码加密
        sysUser.setSalt(salt);
        sysUser.setPwd(PasswordUtil.encrypt(password, salt));

        // 如果头像为空，则设置默认头像 todo
//        if (StringUtils.isBlank(sysUser.getHead())) {
//            sysUser.setHead(springBootPlusProperties.getLoginInitHead());
//        }

        // 保存系统用户
        return sysUser.insert();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(SysUser sysUser) throws Exception {
        // 校验部门和角色
        checkDepartmentAndRole(sysUser.getDeptId(), sysUser.getRoleId());
        // 获取系统用户
        SysUser updateSysUser = getById(sysUser.getId());
        if (updateSysUser == null) {
            throw new BusinessException("修改的用户不存在");
        }

        // 修改系统用户
        updateSysUser.setNickname(sysUser.getNickname())
                .setPhone(sysUser.getPhone())
                .setRemark(sysUser.getRemark())
                .setStatus(sysUser.getStatus())
                .setDeptId(sysUser.getDeptId())
                .setRoleId(sysUser.getRoleId())
                .setUpdateTime(new Date());
        return super.updateById(updateSysUser);
    }

    @Override
    public SysUser getUserSingle(Integer id) {
        LambdaQueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>().lambda()
                .select(SysUser.class, info -> !"password".equals(info.getProperty()))
                .eq(SysUser::getId, id);
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
        SysUser sysUser = new SysUser().selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, oldUsername));
        if (null == sysUser) {
            throw new TipException("该用户名不存在");
        }

        sysUser.setUsername(newUsername);
        sysUser.setEmail(email);

        return sysUser.updateById();
    }

    @Override
    public void checkDepartmentAndRole(Long departmentId, Long roleId) throws Exception {
        // 校验部门是否存在并且可用
        boolean isEnableDepartment = sysDepartmentService.isEnableSysDepartment(departmentId);
        if (!isEnableDepartment) {
            throw new BusinessException("该部门不存在或已禁用");
        }
        // 校验角色是否存在并且可用
        boolean isEnableRole = sysRoleService.isEnableSysRole(roleId);
        if (!isEnableRole) {
            throw new BusinessException("该角色不存在或已禁用");
        }
    }
}
