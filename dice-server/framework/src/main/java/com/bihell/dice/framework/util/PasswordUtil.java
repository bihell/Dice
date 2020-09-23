package com.bihell.dice.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 密码加密工具类 todo
 */
@Slf4j
public class PasswordUtil {

    /**
     * 密码加盐，再加密
     *
     * @param pwd
     * @param salt
     * @return
     */
    public static String encrypt(String pwd, String salt) {
        if (StringUtils.isBlank(pwd)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (StringUtils.isBlank(salt)) {
            throw new IllegalArgumentException("盐值不能为空");
        }
        return DigestUtils.sha256Hex(pwd + salt);
    }

}
