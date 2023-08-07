package com.bihell.dice.auth.annotation;

import java.lang.annotation.*;

/**
 * @author geekidea
 * @date 2022/6/26
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    /**
     * 权限编码
     *
     * @return
     */
    String value();

    /**
     * 角色编码
     *
     * @return
     */
    String role() default "";

    /**
     * 角色编码数组
     *
     * @return
     */
    String[] roles() default {};

}
