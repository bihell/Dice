package com.bihell.dice.model.params;

import lombok.Data;

/**
 * 接口请求参数
 *
 * @author haseochen
 */
@Data
public class Param {
    private String projectType;
    /**
     * 搜索词
     */
    private String criteria;
    private Integer roleId;
}
