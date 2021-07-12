package com.bihell.dice.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum KeepaliveEnum {

    /**
     * 缓存
     */
    NO(0, "不缓存"),
    /**
     * 不缓存
     */
    YES(1, "缓存");

    private Integer code;
    private String desc;

}
