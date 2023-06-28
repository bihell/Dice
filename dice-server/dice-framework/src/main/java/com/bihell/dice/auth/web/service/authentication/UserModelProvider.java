package com.bihell.dice.auth.web.service.authentication;


import com.bihell.dice.framework.model.UserModel;
import com.bihell.dice.system.vo.SysUserSecurityVo;

/**
 * 用户模型提供者
 *
 * @author Tang
 */
public interface UserModelProvider {

    /**
     * 创建用户模型
     *
     * @param user      用户信息
     * @param password  明文密码
     * @param account   登陆账号
     * @param loginType 登陆类型
     * @return 用户模型
     */
    UserModel createUserModel(SysUserSecurityVo user, String password, String account, String loginType) throws Exception;

}
