package com.bihell.dice.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口请求参数
 *
 * @author haseochen
 */
@Data
public class QueryParam {
    private String projectType;

    /**
     * 搜索词
     */
    private String criteria;

    private Integer roleId;

    private Integer itemId;

    /**
     * 页码
     */
    private int pageNum = 1;

    /**
     * 每页大小
     */
    private int pageSize = 13;

    private Integer userId;

    private Integer roleType;

    private Integer userType;
}
