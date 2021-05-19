package com.bihell.dice.framework.core.pagination;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.config.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果对象
 * @author haseochen
 */

@Slf4j
@Data
@ApiModel("分页结果对象")
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = 4784961132604516495L;

    @ApiModelProperty("总行数")
    @JSONField(name = CommonConstant.PAGE_TOTAL_NAME)
    @JsonProperty(CommonConstant.PAGE_TOTAL_NAME)
    private long total = 0;

    @ApiModelProperty("数据列表")
    @JSONField(name = CommonConstant.PAGE_RECORDS_NAME)
    @JsonProperty(CommonConstant.PAGE_RECORDS_NAME)
    private List<T> records = Collections.emptyList();

    @ApiModelProperty(value = "页码")
    @JSONField(name = CommonConstant.PAGE_INDEX_NAME)
    @JsonProperty(CommonConstant.PAGE_INDEX_NAME)
    private Long pageIndex;

    @ApiModelProperty(value = "页大小")
    @JSONField(name = CommonConstant.PAGE_SIZE_NAME)
    @JsonProperty(CommonConstant.PAGE_SIZE_NAME)
    private Long pageSize;

    public Paging() {

    }

    public Paging(IPage<T> page) {
        this.total = page.getTotal();
        this.records = page.getRecords();
        this.pageIndex = page.getCurrent();
        this.pageSize = page.getSize();
    }

}
