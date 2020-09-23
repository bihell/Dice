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
        String token = "first-login-rest-pwd-token:" + UUIDUtil.getUuid();
        return token;
    }

    /**
     * 生成验证码token
     * @return
     */
    public static String generateVerificationCodeToken(){
        String token = "verification-code-token:" + UUIDUtil.getUuid();
        return token;
    }
}
