package com.bihell.dice.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.system.entity.SysUser;
import com.bihell.dice.system.param.UserPageParam;
import com.bihell.dice.system.vo.RouteItemVO;
import com.bihell.dice.system.vo.SysUserQueryVo;
import com.bihell.dice.system.vo.SysUserVo;

import java.util.List;

/**
 * User Service 接口
 *
 * @author bihell
 * @since 2017/7/12 21:25
 */
public interface UserService extends IService<SysUser> {

    void addUser(SysUserVo sysUserVo) throws Exception;

    /**
     * 修改
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    boolean updateUser(SysUserVo sysUserVo) throws Exception;

    SysUser getUserSingle(Integer id);

    /**
     * 修改用户信息
     *
     * @param oldUsername 原用户名
     * @param newUsername 新用户名
     * @param email       邮箱
     * @return boolean
     */
    boolean resetUser(String oldUsername, String newUsername, String email);

//    /**
//     * 获取用户列表
//     * @return
//     */
//    IPage<User> getUserList(QueryParam queryParam);

    void assignRole(SysUser sysUser);

    /**
     * 获取用户列表
     * @param userPageParam
     * @return
     */
    Paging<SysUserQueryVo> getUserPageList(UserPageParam userPageParam);

    /**
     * 检验部门和角色是否存在并且已启用
     *
     * @param departmentId
     * @param roleId
     * @throws Exception
     */
    void checkDepartmentAndRole(Long departmentId, Long roleId) throws Exception;

    /**
     * 获取菜单列表
     *
     * @return
     */
    List<RouteItemVO> getMenuList() throws Exception;

    /**
     * 获取权限标识
     *
     * @return
     */
    List<String> getPermCode() throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteSysUser(Long id) throws Exception;
}
