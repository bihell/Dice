package com.bihell.dice.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否内嵌
 *
 * @author Kevin
 */
@Getter
@AllArgsConstructor
public enum FrameEnum {

    /**
     * 内嵌
     */
    YES(0, "内嵌"),
    /**
     * 不内嵌
     */
    NO(1, "不内嵌");

    private Integer code;
    private String desc;

}
