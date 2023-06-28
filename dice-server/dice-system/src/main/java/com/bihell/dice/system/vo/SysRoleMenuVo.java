package com.bihell.dice.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * 角色权限关系 查询结果对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@Schema(description = "角色权限关系查询参数")
public class SysRoleMenuVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "权限id")
    private Long permissionId;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer state;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

}