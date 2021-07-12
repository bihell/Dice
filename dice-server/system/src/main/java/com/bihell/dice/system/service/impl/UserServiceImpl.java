package com.bihell.dice.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.framework.common.exception.BusinessException;
import com.bihell.dice.framework.common.exception.TipException;
import com.bihell.dice.framework.core.pagination.PageInfo;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.shiro.util.SaltUtil;
import com.bihell.dice.framework.util.LoginUtil;
import com.bihell.dice.framework.util.PasswordUtil;
import com.bihell.dice.framework.util.PhoneUtil;
import com.bihell.dice.system.entity.*;
import com.bihell.dice.system.enums.FrameEnum;
import com.bihell.dice.system.enums.KeepaliveEnum;
import com.bihell.dice.system.enums.LinkExternalEnum;
import com.bihell.dice.system.enums.MenuLevelEnum;
import com.bihell.dice.system.mapper.SysRolePermissionMapper;
import com.bihell.dice.system.mapper.UserMapper;
import com.bihell.dice.system.param.UserPageParam;
import com.bihell.dice.system.service.*;
import com.bihell.dice.system.vo.RouteItemVO;
import com.bihell.dice.system.vo.RouteMetoVO;
import com.bihell.dice.system.vo.SysRolePermissionQueryVo;
import com.bihell.dice.system.vo.SysUserQueryVo;
import com.google.common.collect.Lists;
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
import java.util.Set;
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
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @Lazy
    @Autowired
    private SysRoleService sysRoleService;

    @Lazy
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @Lazy
    @Autowired
    private SysPermissionService sysPermissionService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) throws Exception {
        return super.removeById(id);
    }

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

    @Override
    public List<RouteItemVO> getMenuList() throws Exception {
        List<SysPermission> sysPermissions;
        SysUser sysUser = new SysUser().selectById(LoginUtil.getUserId());
        SysRole sysRole = new SysRole().selectById(sysUser.getRoleId());
        if ("admin".equals(sysRole.getCode())) {
            sysPermissions = sysPermissionService.list(Wrappers.lambdaQuery(SysPermission.class).in(SysPermission::getLevel, MenuLevelEnum.ONE.getCode(), MenuLevelEnum.TWO.getCode()));
        } else {
            // 查询菜单
            List<SysRolePermission> sysRoleMenuList = new SysRolePermission().selectList(
                    new QueryWrapper<SysRolePermission>().lambda().eq(SysRolePermission::getRoleId,sysUser.getRoleId()));
            if (sysRoleMenuList.isEmpty()) {
                sysPermissions = Lists.newArrayList();
            } else {
                Set<Long> menuIds = sysRoleMenuList.stream().map(SysRolePermission::getPermissionId).collect(Collectors.toSet());
                sysPermissions = sysPermissionService.list(Wrappers.lambdaQuery(SysPermission.class)
                        .in(SysPermission::getLevel, MenuLevelEnum.ONE.getCode(), MenuLevelEnum.TWO.getCode())
                        .in(SysPermission::getId,menuIds)
                );
            }
        }

        List<RouteItemVO> routeItemVOList = sysPermissions.stream().filter(item -> item.getParentId() == null).map(item -> {
            RouteItemVO node = new RouteItemVO();
            node.setPath(item.getLevel().equals(MenuLevelEnum.ONE.getCode()) ? "/" + item.getRoutePath() : item.getRoutePath());

            node.setComponent(item.getLevel().equals(MenuLevelEnum.ONE.getCode()) && item.getParentId() == null ? "LAYOUT" : item.getComponent());

            node.setName(StrUtil.upperFirst(item.getRoutePath()));
            node.setMeta(new RouteMetoVO());

            RouteMetoVO routeMetoVO = new RouteMetoVO();
            routeMetoVO.setTitle(item.getName());
            routeMetoVO.setIcon(item.getIcon());
            if (item.getLevel().equals(MenuLevelEnum.TWO.getCode())) {
                routeMetoVO.setIgnoreKeepAlive(item.getKeepAlive().equals(KeepaliveEnum.YES.getCode()));
                if (item.getIsExt().equals(LinkExternalEnum.YES.getCode())) {
                    if (item.getFrame().equals(FrameEnum.YES.getCode())) {
                        routeMetoVO.setFrameSrc(item.getComponent());
                    }
                    if (item.getFrame().equals(FrameEnum.NO.getCode())) {
                        node.setPath(item.getComponent());
                    }
                }
            }
            node.setMeta(routeMetoVO);
            node.setChildren(getChildrenList(item, sysPermissions));
            return node;
        }).collect(Collectors.toList());
        return routeItemVOList;
    }

    @Override
    public List<String> getPermCode() throws Exception {
        return sysPermissionService.getPermissionCodesByUserId(LoginUtil.getUserId());
    }

    private List<RouteItemVO> getChildrenList(SysPermission root, List<SysPermission> list) {
        System.out.println(root);
        List<RouteItemVO> childrenList = list.stream().filter(item -> item.getParentId() != null && item.getParentId().equals(root.getId())).map(item -> {
            RouteItemVO node = new RouteItemVO();
            node.setPath(item.getLevel().equals(MenuLevelEnum.ONE.getCode()) ? "/" + item.getRoutePath() : item.getRoutePath());
            node.setComponent(item.getLevel().equals(MenuLevelEnum.ONE.getCode()) && item.getParentId() == null ? "LAYOUT" : item.getComponent());
            node.setName(StrUtil.upperFirst(item.getRoutePath()));
            node.setMeta(new RouteMetoVO());

            RouteMetoVO routeMetoVO = new RouteMetoVO();
            routeMetoVO.setTitle(item.getName());
            routeMetoVO.setIcon(item.getIcon());
            if (item.getLevel().equals(MenuLevelEnum.TWO.getCode())) {
                routeMetoVO.setIgnoreKeepAlive(!item.getKeepAlive().equals(KeepaliveEnum.YES.getCode()));
                if (item.getIsExt().equals(LinkExternalEnum.YES.getCode())) {
                    if (item.getFrame().equals(FrameEnum.YES.getCode())) {
                        routeMetoVO.setFrameSrc(item.getComponent());
                    }
                    if (item.getFrame().equals(FrameEnum.NO.getCode())) {
                        node.setPath(item.getComponent());
                    }
                }
            }
            node.setMeta(routeMetoVO);
            node.setChildren(getChildrenList(item, list));
            return node;
        }).collect(Collectors.toList());
        return childrenList;
    }
}
