package com.bihell.dice.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 部门TreeVo
 * </pre>
 *
 * @author geekidea
 * @since 2019-11-1
 */
@Data
@Accessors(chain = true)
@Schema(description = "部门")
public class SysDepartmentTreeVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -2250233632748939400L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "父id")
    private Long parentId;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer state;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    private List<SysDepartmentTreeVo> children;

}
