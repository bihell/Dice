package com.bihell.dice.framework.util;

import java.util.UUID;

/**
 * todo
 */
public class UUIDUtil {

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
    
}
