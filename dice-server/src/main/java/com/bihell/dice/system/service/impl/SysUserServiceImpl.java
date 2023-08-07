package com.bihell.dice.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.commons.exception.BusinessException;
import com.bihell.dice.commons.service.impl.BaseServiceImpl;
import com.bihell.dice.commons.vo.RoleInfoVO;
import com.bihell.dice.commons.pagination.PageInfo;
import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.commons.utils.PhoneUtil;
import com.bihell.dice.commons.utils.SecurityUtil;
import com.bihell.dice.system.dto.SysUserDto;
import com.bihell.dice.system.entity.SysMenu;
import com.bihell.dice.system.entity.SysRoleMenu;
import com.bihell.dice.system.entity.SysUser;
import com.bihell.dice.system.entity.SysUserRole;
import com.bihell.dice.system.enums.FrameEnum;
import com.bihell.dice.system.enums.KeepaliveEnum;
import com.bihell.dice.system.enums.LinkExternalEnum;
import com.bihell.dice.system.enums.MenuLevelEnum;
import com.bihell.dice.system.mapper.SysUserMapper;
import com.bihell.dice.system.param.UserPageParam;
import com.bihell.dice.system.service.*;
import com.bihell.dice.system.vo.RouteItemVO;
import com.bihell.dice.system.vo.RouteMetoVO;
import com.bihell.dice.system.vo.SysUserQueryVo;
import com.bihell.dice.system.vo.SysUserSecurityVo;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final DiceProperties diceProperties;
    private final SysUserRoleService sysUserRoleService;

    @Autowired
    private SysDeptService sysDeptService;

    @Lazy
    @Autowired
    private SysRoleService sysRoleService;

    @Lazy
    @Autowired
    private SysMenuService sysMenuService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysUser(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<SysUserQueryVo> getUserPageList(UserPageParam userPageParam) {
        Page<SysUserQueryVo> page = new PageInfo<>(userPageParam, OrderItem.desc("create_time"));
        IPage<SysUserQueryVo> iPage = sysUserMapper.getSysUserPageList(page, userPageParam);

        if (iPage != null && org.apache.commons.collections4.CollectionUtils.isNotEmpty(iPage.getRecords())) {
            // 手机号码脱敏处理
            iPage.getRecords().forEach(vo -> {
                vo.setPhone(PhoneUtil.desensitize(vo.getPhone()));
            });

            // 设置角色id
            iPage.getRecords().forEach(item -> {
                List<SysUserRole> sysUserRoleList = sysUserRoleService.list(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, item.getId()));
                item.setRoleIds(sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
            });
        }
        return new Paging(iPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(SysUserDto sysUserDto) throws Exception {
        SysUser sysUser = BeanUtil.copyProperties(sysUserDto, SysUser.class);

        // 校验用户名是否存在
        boolean isExists = sysUser.selectCount(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, sysUser.getUsername()).or().eq(SysUser::getEmail, sysUser.getEmail())) > 0;
        if (isExists) {
            throw new BusinessException("用户名或邮箱已存在");
        }

        sysUser.setId(null);
        String password = sysUser.getPassword();
        // 如果密码为空，则设置默认密码
        if (StringUtils.isBlank(password)) {
            password = diceProperties.getLoginInitPassword();
        }
        // 密码加密
        sysUser.setPassword(SecurityUtil.encryptPassword(password));

        // 如果头像为空，则设置默认头像
        if (StringUtils.isBlank(sysUser.getAvatar())) {
            sysUser.setAvatar(diceProperties.getLoginInitHead());
        }

        sysUser.insert();

        // 删除用户与角色关联
        sysUserRoleService.remove(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, sysUser.getId()));

        // 新增用户与角色关联
        if (Objects.nonNull(sysUserDto.getRoleIds())) {
            List<SysUserRole> sysUserRoleList = sysUserDto.getRoleIds().stream().map(item -> {
                // 校验部门和角色
                try {
                    checkDepartmentAndRole(sysUser.getDeptId(), item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(item);
                return sysUserRole;
            }).collect(Collectors.toList());
            sysUserRoleService.saveBatch(sysUserRoleList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(SysUserDto sysUserDto) throws Exception {

        // 获取系统用户
        SysUser updateSysUser = getById(sysUserDto.getId());
        if (updateSysUser == null) {
            throw new BusinessException("修改的用户不存在");
        }

        // 删除用户与角色关联
        sysUserRoleService.remove(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, updateSysUser.getId()));

        // 新增用户与角色关联
        if (Objects.nonNull(sysUserDto.getRoleIds())) {
            List<SysUserRole> sysUserRoleList = sysUserDto.getRoleIds().stream().map(item -> {
                // 校验部门和角色
                try {
                    checkDepartmentAndRole(sysUserDto.getDeptId(), item);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUserDto.getId());
                sysUserRole.setRoleId(item);
                return sysUserRole;
            }).collect(Collectors.toList());
            sysUserRoleService.saveBatch(sysUserRoleList);
        }

        updateSysUser = BeanUtil.copyProperties(sysUserDto, SysUser.class);
        updateSysUser.setUpdateTime(new Date());

        return super.updateById(updateSysUser);
    }

    @Override
    public SysUser selectUserById(Integer id) {
        LambdaQueryWrapper<SysUser> wrapper = new QueryWrapper<SysUser>().lambda()
                .select(SysUser.class, info -> !"password".equals(info.getProperty()))
                .eq(SysUser::getId, id);
        return sysUserMapper.selectOne(wrapper);
    }

    @Override
    public SysUserSecurityVo selectUserByUsername(String username) {
        return sysUserMapper.selectUserByUsername(username);
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
            throw new BusinessException("该用户名不存在");
        }

        sysUser.setUsername(newUsername);
        sysUser.setEmail(email);

        return sysUser.updateById();
    }

    @Override
    public void checkDepartmentAndRole(Long departmentId, Long roleId) throws Exception {
        // 校验部门是否存在并且可用
        boolean isEnableDepartment = sysDeptService.isEnableSysDepartment(departmentId);
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
        List<SysMenu> sysMenus;

        if (SecurityUtil.getRoles().stream().anyMatch(item -> item.getValue().equals("admin"))) {
            sysMenus = sysMenuService.list(Wrappers.lambdaQuery(SysMenu.class)
                    .in(SysMenu::getLevel, MenuLevelEnum.ONE.getCode(), MenuLevelEnum.TWO.getCode())
                    .orderByAsc(SysMenu::getSort)
            );
        } else {
            // 查询菜单
            List<Long> roleIdList = SecurityUtil.getRoles().stream().map(RoleInfoVO::getId).collect(Collectors.toList());
            List<SysRoleMenu> sysRoleMenuList = new SysRoleMenu().selectList(
                    new QueryWrapper<SysRoleMenu>().lambda().in(SysRoleMenu::getRoleId,roleIdList));
            if (sysRoleMenuList.isEmpty()) {
                sysMenus = Lists.newArrayList();
            } else {
                Set<Long> menuIds = sysRoleMenuList.stream().map(SysRoleMenu::getPermissionId).collect(Collectors.toSet());
                sysMenus = sysMenuService.list(Wrappers.lambdaQuery(SysMenu.class)
                        .in(SysMenu::getLevel, MenuLevelEnum.ONE.getCode(), MenuLevelEnum.TWO.getCode())
                        .in(SysMenu::getId,menuIds)
                        .orderByAsc(SysMenu::getSort)
                );
            }
        }

        List<RouteItemVO> routeItemVOList = sysMenus.stream().filter(item -> item.getParentId() == null).map(item -> {
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
            node.setChildren(getChildrenList(item, sysMenus));
            return node;
        }).collect(Collectors.toList());
        return routeItemVOList;
    }

    @Override
    public Set<String> getPermCode() {
        return sysMenuService.getPermissionCodesByUserId(SecurityUtil.getUser().getUserId());
    }

    private List<RouteItemVO> getChildrenList(SysMenu root, List<SysMenu> list) {
        List<RouteItemVO> childrenList = list.stream().filter(item -> item.getParentId() != null && item.getParentId().equals(root.getId())).map(item -> {
            RouteItemVO node = new RouteItemVO();
            node.setPath(item.getLevel().equals(MenuLevelEnum.ONE.getCode()) ? "/" + item.getRoutePath() : item.getRoutePath());
            node.setComponent(item.getLevel().equals(MenuLevelEnum.ONE.getCode()) && item.getParentId() == null ? "LAYOUT" : item.getComponent());
            node.setName(StrUtil.upperFirst(item.getRoutePath()));
            node.setMeta(new RouteMetoVO());

            RouteMetoVO routeMetoVO = new RouteMetoVO();
            routeMetoVO.setTitle(item.getName());
            routeMetoVO.setIcon(item.getIcon());
            routeMetoVO.setHideMenu(item.getIsShow()==0);
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

    /**
     * 修改用户头像
     *
     * @param userId     用户主键
     * @param avatarPath 头像路径
     * @return 影响行数
     */
    @Override
    public int updateAvatarByUserId(Long userId, String avatarPath) {
        return sysUserMapper.updateAvatarByUserId(userId, avatarPath);
    }
}
