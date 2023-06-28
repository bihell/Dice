package com.bihell.dice.framework.log.annotation;

import java.lang.annotation.*;

/**
 * 忽略操作日志记录注解
 * 在controller上标注该方法后，将不会记录操作日志
 * 可以标注在类和方法上，如果标记在类上，则会忽略controller中的所有方法
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogIgnore {

}
