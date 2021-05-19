package com.bihell.dice.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author haseochen
 */
@Data
@Accessors(chain = true)
@ApiModel("前端标签和分类Select值")
public class MetaQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("Select名")
    private String label;

    @ApiModelProperty("Select值")
    private Integer value;
}
