package com.bihell.dice.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <pre>
 * 系统角色 查询结果对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@Schema(description = "系统角色查询参数")
public class SysRoleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色唯一编码")
    private String code;

    @Schema(description = "角色类型")
    private Integer type;

    @Schema(description = "角色状态，0：禁用，1：启用")
    private Integer state;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "权限集合")
    private Set<Long> permissions;

}