package com.bihell.dice.framework.annotation;


import com.bihell.dice.commons.enums.SysLogEnum;

/**
 * @author geekidea
 * @date 2022/8/3
 **/
public @interface Log {

    /**
     * 描述
     *
     * @return
     */
    String value() default "";


    SysLogEnum type() default SysLogEnum.OTHER;

}
