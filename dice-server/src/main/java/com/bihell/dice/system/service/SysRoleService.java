package com.bihell.dice.system.service;

import com.bihell.dice.commons.service.BaseService;
import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.system.entity.SysMenu;
import com.bihell.dice.system.entity.SysRole;
import com.bihell.dice.system.param.sysrole.SysRolePageParam;
import com.bihell.dice.system.param.sysrole.UpdateSysRolePermissionParam;
import com.bihell.dice.system.vo.SysRoleVo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 系统角色 服务类
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
public interface SysRoleService extends BaseService<SysRole> {

    /**
     * 保存
     * @param sysRole
     * @return
     * @throws Exception
     */
    boolean saveSysRole(SysRole sysRole) throws Exception;

    /**
     * 修改
     * @param sysRole
     * @return
     * @throws Exception
     */
    boolean updateSysRole(SysRole sysRole) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysRole(Long id) throws Exception;

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    SysRoleVo getSysRoleById(Serializable id) throws Exception;

    /**
     * 获取分页对象
     *
     * @param sysRolePageParam
     * @return
     * @throws Exception
     */
    Paging<SysRole> getSysRolePageList(SysRolePageParam sysRolePageParam) throws Exception;

    /**
     * 根据id校验角色是否存在并且已启用
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean isEnableSysRole(Long id) throws Exception;

    /**
     * 判断角色编码是否存在
     *
     * @param code
     * @return
     * @throws Exception
     */
    boolean isExistsByCode(String code) throws Exception;

    /**
     * 修改系统角色权限配置
     *
     * @param param
     * @return
     * @throws Exception
     */
    boolean updateSysRolePermission(UpdateSysRolePermissionParam param) throws Exception;

    /**
     * 查询角色关联的菜单
     *
     * @return
     */
    List<SysMenu> listRoleMenus(String roleId);

    /**
     * 根据用户主键获取角色主键集合
     *
     * @param userId 用户主键
     * @return 角色主键集合
     */
    List<Long> getRoleIdsByUserId(Long userId);

    /**
     * 根据用户主键获取角色集合
     *
     * @param userId 用户主键
     * @return 角色集合
     */
    Set<String> getRolesByUserId(Long userId);
}
