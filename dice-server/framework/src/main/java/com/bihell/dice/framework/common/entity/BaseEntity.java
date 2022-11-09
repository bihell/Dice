package com.bihell.dice.framework.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author haseochen
 */
@Getter
@Setter
public class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable {
    private static final long serialVersionUID = -7176390653391227433L;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    @Null(message = "创建时间不用传")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Null(message = "修改时间不用传")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人")
    @TableField(exist = false)
    private Long creator;

    @ApiModelProperty("修改人")
    @TableField(exist = false)
    private Integer modifier;

    @ApiModelProperty("逻辑删除")
    // 逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableLogic
    private Integer isDeleted;
}
