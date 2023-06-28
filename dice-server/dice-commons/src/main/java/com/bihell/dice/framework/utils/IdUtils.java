package com.bihell.dice.framework.utils;

import java.util.UUID;

/**
 * id 工具类
 *
 * @author Tang
 */
public class IdUtils {

    private IdUtils() {
    }

    /**
     * 获取 uuid
     * @return uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
