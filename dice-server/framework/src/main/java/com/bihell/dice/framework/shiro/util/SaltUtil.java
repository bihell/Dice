package com.bihell.dice.framework.shiro.util;

import com.bihell.dice.config.properties.JwtProperties;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * 盐值包装工具类 todo
 **/
public class SaltUtil {

    /**
     * 盐值包装
     *
     * @param secret 配置文件中配置的附加盐值
     * @param salt   数据库中保存的盐值
     * @return
     */
    public static String getSalt(String secret, String salt) {
        if (StringUtils.isBlank(secret) && StringUtils.isBlank(salt)) {
            return null;
        }
        // 加密方法
        String newSalt = DigestUtils.sha256Hex(secret + salt);
        return newSalt;
    }

    /**
     * 生成32位随机盐
     *
     * @return
     */
    public static String generateSalt() {
        return new SecureRandomNumberGenerator().nextBytes(16).toHex();
    }

    /**
     * 加工盐值
     *
     * @param salt
     * @param jwtProperties
     * @return
     */
    public static String getSalt(String salt, JwtProperties jwtProperties) {
        String newSalt;
        if (jwtProperties.isSaltCheck()) {
            // 包装盐值
            newSalt = SaltUtil.getSalt(jwtProperties.getSecret(), salt);
        } else {
            newSalt = jwtProperties.getSecret();
        }
        return newSalt;
    }

}

