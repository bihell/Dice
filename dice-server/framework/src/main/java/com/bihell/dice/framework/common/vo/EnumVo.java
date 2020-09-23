package com.bihell.dice.framework.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 枚举类型VO todo
 **/
@Data
@Accessors(chain = true)
public class EnumVo<T> {

    /**
     * 枚举code
     */
    private Integer code;

    /**
     * 枚举描述
     */
    private String desc;

    /**
     * 枚举类型
     */
    private T baseEnum;

}
