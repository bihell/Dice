package com.bihell.dice.auth.web.service.authentication;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.auth.web.service.TokenService;
import com.bihell.dice.framework.constant.Status;
import com.bihell.dice.framework.exception.status.DeletedException;
import com.bihell.dice.framework.exception.status.DisabledException;
import com.bihell.dice.framework.exception.user.PasswordMismatchException;
import com.bihell.dice.framework.exception.user.UserNotFoundException;
import com.bihell.dice.framework.model.SysDeptModel;
import com.bihell.dice.framework.model.SysUserModel;
import com.bihell.dice.framework.model.UserModel;
import com.bihell.dice.framework.vo.RoleInfoVO;
import com.bihell.dice.framework.utils.SecurityUtils;
import com.bihell.dice.system.entity.SysRole;
import com.bihell.dice.system.entity.SysUserRole;
import com.bihell.dice.system.service.SysLogLoginService;
import com.bihell.dice.system.service.SysMenuService;
import com.bihell.dice.system.service.SysRoleService;
import com.bihell.dice.system.vo.SysUserSecurityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户权限
 *
 * @author Tang
 */
@Component
public class AuthenticationService implements UserModelProvider {

    private final SysRoleService roleService;

    private final SysMenuService menuService;

    private final SysLogLoginService logLoginService;

    private final TokenService tokenService;

    public AuthenticationService(SysRoleService roleService, SysMenuService menuService, SysLogLoginService logLoginService, TokenService tokenService) {
        this.roleService = roleService;
        this.menuService = menuService;
        this.logLoginService = logLoginService;
        this.tokenService = tokenService;
    }

    /**
     * 创建用户模型
     *
     * @param user 用户信息
     * @param password 明文密码
     * @param loginType 登陆类型
     * @return 用户模型
     */
    public UserModel createUserModel(SysUserSecurityVo user, String password, String account, String loginType) throws Exception {
        try {
            if (user == null) {
                throw new UserNotFoundException("用户不存在");
            }
            if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
                throw new PasswordMismatchException("密码错误");
            }
            if (user.getStatus().equals(Status.DISABLED)) {
                throw new DisabledException("账号已停用");
            }
            if (user.getIsDeleted().equals(Status.DELETED)) {
                throw new DeletedException("账号已删除");
            }
        } catch (RuntimeException e) {
            logLoginService.recordLoginInfo(user == null ? null : user.getUserId(), tokenService.getUserAgent(), account, loginType, false, e.getMessage());
            throw e;
        }

        var userModel = new UserModel();
        // 用户对象
        var sysUserModel = new SysUserModel();
        var sysDeptModel = new SysDeptModel();
        BeanUtils.copyProperties(user, sysUserModel);
        BeanUtils.copyProperties(user.getDept(), sysDeptModel);
        sysUserModel.setDept(sysDeptModel);
        sysUserModel.setRoleIds(roleService.getRoleIdsByUserId(user.getUserId()));
        // 角色集合
        var roles = roleService.getRolesByUserId(user.getUserId());

        // 获取当前用户角色
        List<SysUserRole> sysUserRoles = new SysUserRole().selectList(new QueryWrapper<SysUserRole>().lambda()
                .eq(SysUserRole::getUserId, user.getUserId()));

        List<RoleInfoVO> roleInfoVOList = sysUserRoles.stream().map(
                item -> {
                    SysRole sysRole = roleService.getById(item.getRoleId());
//                    if (sysRole == null) {
//                        throw new AuthenticationException("角色不存在");
//                    }
//                    if (StateEnum.DISABLE.getCode().equals(sysRole.getStatus())) {
//                        throw new AuthenticationException("角色已禁用");
//                    }
                    RoleInfoVO roleInfoVO = new RoleInfoVO();
                    roleInfoVO.setRoleName(sysRole.getRoleName());
                    roleInfoVO.setValue(sysRole.getRoleCode());
                    roleInfoVO.setId(sysRole.getId());
                    return roleInfoVO;
                }
        ).toList();

        // 权限集合
        var permissions = menuService.getPermissionCodesByUserId(user.getUserId());
        // TODO: 2023/6/9 管理员角色默认获得所有权限
//        if (roles.contains(SecurityUtils.ADMIN_ROLE_KEY)) {
//            permissions = Set.of(SecurityUtils.ALL_PERMISSIONS);
//        }
        // 将信息放进登陆用户模型
        userModel.setLoginType(loginType);
        userModel.setUser(sysUserModel);
        userModel.setRoles(roleInfoVOList);
        userModel.setPermissions(permissions);
        return userModel;
    }

}
