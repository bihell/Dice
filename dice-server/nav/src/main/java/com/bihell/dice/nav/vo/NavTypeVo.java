package com.bihell.dice.nav.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "NavType对象")
public class NavTypeVo implements Serializable {
    private static final long serialVersionUID = -2250233632748939400L;

    @ApiModelProperty("导航类型id")
    private Integer id;

    @ApiModelProperty("导航类型名称")
    private String name;

    @ApiModelProperty("是否公开：1是，0否")
    private Integer isPublic;

    @ApiModelProperty("类型图标")
    private String icon;

    @ApiModelProperty("状态，0：禁用，1：启用")
    private Integer status;

    @ApiModelProperty("父id")
    private Integer parentId;

    @ApiModelProperty("排序")
    private Integer sort;

    private List<NavTypeVo> children;
}
