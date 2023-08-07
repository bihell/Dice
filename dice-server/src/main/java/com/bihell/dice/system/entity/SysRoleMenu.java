package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <pre>
 * 角色权限关系
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色权限关系")
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @Schema(description = "权限id")
    @NotNull(message = "权限id不能为空")
    private Long permissionId;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "版本")
    @Null(message = "版本不用传")
    @Version
    private Integer version;

    @Schema(description = "创建时间")
    @Null(message = "创建时间不用传")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    @Null(message = "修改时间不用传")
    private LocalDateTime updateTime;

}
