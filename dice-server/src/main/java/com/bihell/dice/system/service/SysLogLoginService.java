package com.bihell.dice.system.service;

import com.bihell.dice.commons.model.UserModel;
import com.bihell.dice.system.entity.SysLogLogin;

import java.util.List;

/**
 * 登陆日志业务逻辑层接口
 *
 * @author Tang
 */
public interface SysLogLoginService {

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
     * 记录登陆信息
     *
     * @param userId   用户ID
     * @param userModel 用户信息
     * @param account 登陆账号
     * @param loginType 登陆类型
     * @param success 是否成功
     * @param message 返回消息
     */
    void recordLoginInfo(Long userId, UserModel userModel, String account, String loginType, boolean success, String message);

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
