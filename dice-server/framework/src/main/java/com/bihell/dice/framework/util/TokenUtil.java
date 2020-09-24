package com.bihell.dice.framework.util;

/**
 * todo
 */
public class TokenUtil {

    /**
     *
     * @return
     */
    public static String generateFirstLoginRestPwdToken(){
        return "first-login-rest-pwd-token:" + UUIDUtil.getUuid();
    }

    /**
     * 生成验证码token
     * @return
     */
    public static String generateVerificationCodeToken(){
        return "verification-code-token:" + UUIDUtil.getUuid();
    }
}
