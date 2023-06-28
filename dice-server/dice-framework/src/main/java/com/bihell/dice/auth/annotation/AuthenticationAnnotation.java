package com.bihell.dice.auth.annotation;

import org.springframework.stereotype.Component;

import static com.bihell.dice.framework.utils.SecurityUtils.*;


/**
 * 认证表达式
 *
 * @author Tang
 */
@Component("auth")
public class AuthenticationAnnotation {

    /**
     * 验证用户是否有该角色
     *
     * @param role 角色
     * @return 结果
     */
    public boolean hasRole(String role) {
        return hasAnyRole(role);
    }

    /**
     * 验证用户是否有任意角色
     *
     * @param roles 角色列表
     * @return 结果
     */
    public boolean hasAnyRole(String... roles) {
        if (isAdmin()) {
            return true;
        }
        var roleSet = getRoles();
        for (String role : roles) {
            if (roleSet.contains(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户是否有全部角色
     *
     * @param roles 角色列表
     * @return 结果
     */
    public boolean hasAllRole(String... roles) {
        if (isAdmin()) {
            return true;
        }
        var roleSet = getRoles();
        for (String role : roles) {
            if (!roleSet.contains(role)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证用户是否有该权限
     *
     * @param permission 权限
     * @return 结果
     */
    public boolean hasPermission(String permission) {
        return hasAnyPermission(permission);
    }

    /**
     * 验证用户是否有任意权限
     *
     * @param permissions 权限列表
     * @return 结果
     */
    public boolean hasAnyPermission(String... permissions) {
        if (isAdmin()) {
            return true;
        }
        var permissionSet = getPermissions();
        for (String permission : permissions) {
            if (permissionSet.contains(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户是否有全部权限
     *
     * @param permissions 权限列表
     * @return 结果
     */
    public boolean hasAllPermission(String... permissions) {
        if (isAdmin()) {
            return true;
        }
        var permissionSet = getPermissions();
        for (String permission : permissions) {
            if (!permissionSet.contains(permission)) {
                return false;
            }
        }
        return true;
    }

}
