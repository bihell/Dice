package com.bihell.dice.framework.core.pagination;

import com.alibaba.fastjson2.annotation.JSONField;
import com.bihell.dice.framework.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询参数
 * @author haseochen
 */
@Data
@Schema(description = "查询参数对象")
public abstract class BasePageParam implements Serializable {
    private static final long serialVersionUID = -3263921252635611410L;

    @Schema(description = "页码,默认为1", example = "1")
    @JSONField(name = CommonConstant.PAGE_INDEX_NAME)
    @JsonProperty(CommonConstant.PAGE_INDEX_NAME)
    private Long pageIndex = CommonConstant.DEFAULT_PAGE_INDEX;

    @Schema(description = "页大小,默认为10", example = "10")
    private Long pageSize = CommonConstant.DEFAULT_PAGE_SIZE;

    @Schema(description = "搜索字符串", example = "")
    private String keyword;

    public void setPageIndex(Long pageIndex) {
        if (pageIndex == null || pageIndex <= 0) {
            this.pageIndex = CommonConstant.DEFAULT_PAGE_INDEX;
        } else {
            this.pageIndex = pageIndex;
        }
    }

    public void setPageSize(Long pageSize) {
        if (pageSize == null || pageSize <= 0) {
            this.pageSize = CommonConstant.DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

}
