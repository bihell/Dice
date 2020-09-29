package com.bihell.dice.system.enums;

import com.bihell.dice.framework.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用禁用状态枚举
 **/
@Getter
@AllArgsConstructor
public enum StateEnum implements BaseEnum {

    /** 禁用 **/
    DISABLE(0, "禁用"),
    /** 启用 **/
    ENABLE(1, "启用");

    private Integer code;
    private String desc;

}
