package com.bihell.dice.commons.constant;

/**
 * 缓存前缀
 *
 * @author Tang
 */
public class CachePrefix {

    private CachePrefix() {
    }

    /**
     * 缓存前缀
     */
    public static final String SYSTEM = "dice:";

    /**
     * 登陆用户信息缓存前缀
     */
    public static final String LOGIN_TOKENS = SYSTEM + "login_tokens:";

    /**
     * 字典数据缓存前缀
     */
    public static final String DICT_TYPE = SYSTEM + "dict_type:";

}
