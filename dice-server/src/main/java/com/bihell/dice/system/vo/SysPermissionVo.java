package com.bihell.dice.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * 系统权限 查询结果对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@Schema(description = "系统权限查询参数")
public class SysPermissionVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "父id")
    private Long parentId;

    @Schema(description = "路径")
    private String routePath;

    @Schema(description = "唯一编码")
    private String code;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "类型，1：菜单，2：按钮")
    private Integer type;

    @Schema(description = "层级，1：第一级，2：第二级，N：第N级")
    private Integer level;

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

}