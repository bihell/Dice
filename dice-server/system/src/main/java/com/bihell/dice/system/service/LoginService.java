package com.bihell.dice.system.service;

import com.bihell.dice.system.entity.User;
import com.bihell.dice.system.param.LoginParam;
import com.bihell.dice.system.vo.LoginSysUserTokenVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 登录服务接口
 * </p>
 **/
public interface LoginService {

//    /**
//     * 登录
//     *
//     * @param loginParam
//     * @return
//     * @throws Exception
//     */
//    LoginSysUserTokenVo login(LoginParam loginParam) throws Exception;


    /**
     * 退出
     * @param request
     * @throws Exception
     */
    void logout(HttpServletRequest request) throws Exception;

//    /**
//     * 根据用户名获取系统用户对象
//     *
//     * @param username
//     * @return
//     * @throws Exception
//     */
//    User getSysUserByUsername(String username) throws Exception;
//
//    /**
//     * 检查验证码是否正确
//     *
//     * @param verifyToken
//     * @param code
//     * @throws Exception
//     */
//    void checkVerifyCode(String verifyToken, String code) throws Exception;

}
