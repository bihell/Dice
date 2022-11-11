package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.bihell.dice.framework.common.entity.BaseEntity;
import com.bihell.dice.framework.core.validator.groups.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysRole对象", description = "系统角色")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = -487738234353456553L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = Update.class, message = "角色ID不能为空")
    private Long id;

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty("角色唯一编码")
    private String roleCode;

    @ApiModelProperty("角色类型")
    private Integer type;

    @ApiModelProperty("角色状态，0：禁用，1：启用")
    private Integer status;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("版本")
    @Null(message = "版本不用传")
    @Version
    private Integer version;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "创建时间不用传")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Null(message = "修改时间不用传")
    private LocalDateTime updateTime;

    @ApiModelProperty("逻辑删除")
    // 逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableLogic
    private Integer isDeleted;
}
