package com.bihell.dice.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 是否外链
 *
 * @author Kevin
 */
@Getter
@AllArgsConstructor
public enum LinkExternalEnum {

    /**
     * 是
     */
    YES(1, "是"),
    /**
     * 否
     */
    NO(0, "否");

    private Integer code;
    private String desc;

}
