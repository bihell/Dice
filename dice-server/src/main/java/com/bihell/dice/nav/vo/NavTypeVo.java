package com.bihell.dice.nav.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "NavType对象")
public class NavTypeVo implements Serializable {
    private static final long serialVersionUID = -2250233632748939400L;

    @Schema(description = "导航类型id")
    private Integer id;

    @Schema(description = "导航类型名称")
    private String name;

    @Schema(description = "是否公开：1是，0否")
    private Integer isPublic;

    @Schema(description = "类型图标")
    private String icon;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Schema(description = "父id")
    private Integer parentId;

    @Schema(description = "排序")
    private Integer sort;

    private List<NavTypeVo> children;
}
