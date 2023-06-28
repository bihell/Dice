package com.bihell.dice.system.mapper;


import com.bihell.dice.system.entity.SysLogLogin;

import java.util.List;

/**
 * 登陆日志数据访问层
 *
 * @author Tang
 */
public interface SysLogLoginMapper {

    /**
     * 查询登陆日志列表
     *
     * @param sysLogLogin 登陆日志对象
     * @return 登陆日志列表
     */
    List<SysLogLogin> selectSysLogLoginList(SysLogLogin sysLogLogin);

    /**
     * 查询用户登陆日志列表
     *
     * @param logLogin 登陆日志对象
     * @return 登陆日志列表
     */
    List<SysLogLogin> selectSysLogLoginListByUser(SysLogLogin logLogin);

    /**
     * 通过登陆日志主键查询登陆日志信息
     *
     * @param loginId 登陆日志主键
     * @return 登陆日志信息
     */
    SysLogLogin selectSysLogLoginByLoginId(Long loginId);

    /**
     * 新增登陆日志信息
     *
     * @param sysLogLogin 登陆日志信息
     * @return 影响行数
     */
    int insertSysLogLogin(SysLogLogin sysLogLogin);

    /**
     * 通过登陆日志主键修改登陆日志信息
     *
     * @param sysLogLogin 登陆日志信息
     * @return 影响行数
     */
    int updateSysLogLoginByLoginId(SysLogLogin sysLogLogin);

    /**
     * 通过登陆日志主键删除登陆日志信息
     *
     * @param loginId 登陆日志主键
     * @return 影响行数
     */
    int deleteSysLogLoginByLoginId(Long loginId);

    /**
     * 通过登陆日志主键数组批量删除登陆日志信息
     *
     * @param loginIds 登陆日志主键数组
     * @return 影响行数
     */
    int deleteSysLogLoginByLoginIds(Long[] loginIds);

}
