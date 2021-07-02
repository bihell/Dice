package com.bihell.dice.system.service;


import com.bihell.dice.framework.common.service.BaseService;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.system.entity.SysPermission;
import com.bihell.dice.system.param.SysPermissionPageParam;
import com.bihell.dice.system.vo.SysPermissionQueryVo;
import com.bihell.dice.system.vo.SysPermissionTreeVo;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 系统权限 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysPermissionService extends BaseService<SysPermission> {

    /**
     * 保存
     *
     * @param sysPermission
     * @return
     * @throws Exception
     */
    boolean saveSysPermission(SysPermission sysPermission) throws Exception;

    /**
     * 修改
     *
     * @param sysPermission
     * @return
     * @throws Exception
     */
    boolean updateSysPermission(SysPermission sysPermission) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysPermission(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysPermissionQueryVo getSysPermissionById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysPermissionPageParam
     * @return
     * @throws Exception
     */
    Paging<SysPermissionQueryVo> getSysPermissionPageList(SysPermissionPageParam sysPermissionPageParam) throws Exception;

    /**
     * 判断权限id是否存在
     *
     * @param permissionIds
     * @return
     * @throws Exception
     */
    boolean isExistsByPermissionIds(List<Long> permissionIds) throws Exception;

    /**
     * 获取所有菜单列表
     *
     * @return
     * @throws Exception
     */
    List<SysPermission> getAllMenuList() throws Exception;

    /**
     * 转换权限列表为树形菜单
     *
     * @param sysPermissions
     * @return
     * @throws Exception
     */
    List<SysPermissionTreeVo> convertSysPermissionTreeVoList(List<SysPermission> sysPermissions) throws Exception;

    /**
     * 获取获取菜单树形列表
     *
     * @return
     * @throws Exception
     */
    List<SysPermissionTreeVo> getAllMenuTree() throws Exception;

    /**
     * 根据用户id获取该用户所有权限编码
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<String> getPermissionCodesByUserId(Long userId) throws Exception;

    /**
     * 根据用户id获取菜单列表
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<SysPermission> getMenuListByUserId(Long userId) throws Exception;

    /**
     * 根据用户id获取菜单树形列表
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<SysPermissionTreeVo> getMenuTreeByUserId(Long userId) throws Exception;

    /**
     * 根据角色id获取该对应的所有三级权限ID
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    List<Long> getPermissionIdsByRoleId(Long roleId) throws Exception;


    /**
     * 获取所有导航菜单(一级/二级菜单)
     *
     * @return
     * @throws Exception
     */
    List<SysPermissionTreeVo> getNavMenuTree() throws Exception;
}
