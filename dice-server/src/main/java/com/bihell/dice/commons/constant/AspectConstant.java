package com.bihell.dice.commons.constant;

/**
 * @author geekidea
 * @date 2022/7/3
 **/
public interface AspectConstant {

    String COMMON_POINTCUT = "execution(public * " + CommonConstant.PACKAGE_NAME + "..*.controller..*.*(..))";

}
