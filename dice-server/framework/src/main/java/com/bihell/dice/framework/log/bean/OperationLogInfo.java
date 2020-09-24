package com.bihell.dice.framework.log.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 操作日志信息 todo
 **/
@Data
@Accessors(chain = true)
public class OperationLogInfo {

    /**
     * 是否忽略
     */
    private boolean ignore;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 日志名称
     */
    private String name;

    /**
     * 日志类型
     */
    private Integer type;

    /**
     * 日志备注
     */
    private String remark;

    /**
     * controller类名称
     */
    private String controllerClassName;

    /**
     * controller目标方法名称
     */
    private String controllerMethodName;

}
