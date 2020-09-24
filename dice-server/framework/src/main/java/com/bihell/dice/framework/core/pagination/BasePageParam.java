package com.bihell.dice.framework.core.pagination;

import com.bihell.dice.config.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询参数 todo
 */
@Data
@ApiModel("查询参数对象")
public abstract class BasePageParam implements Serializable {
    private static final long serialVersionUID = -3263921252635611410L;

    @ApiModelProperty(value = "页码,默认为1", example = "1")
    private Long pageIndex = CommonConstant.DEFAULT_PAGE_INDEX;

    @ApiModelProperty(value = "页大小,默认为10", example = "10")
    private Long pageSize = CommonConstant.DEFAULT_PAGE_SIZE;

    @ApiModelProperty(value = "搜索字符串", example = "")
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
