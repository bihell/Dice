package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bihell.dice.framework.core.validator.groups.Update;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统角色")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = -487738234353456553L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = Update.class, message = "角色ID不能为空")
    private Long id;

    @Schema(description = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @Schema(description = "角色唯一编码")
    private String roleCode;

    @Schema(description = "角色类型")
    private Integer type;

    @Schema(description = "角色状态，0：禁用，1：启用")
    private Integer status;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "版本")
    @Null(message = "版本不用传")
    @Version
    private Integer version;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "创建时间不用传")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Null(message = "修改时间不用传")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "逻辑删除")
    // 逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableLogic
    private Integer isDeleted;
}
