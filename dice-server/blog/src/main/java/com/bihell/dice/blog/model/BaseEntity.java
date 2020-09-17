package com.bihell.dice.blog.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author haseochen
 */
@Getter
@Setter
public class BaseEntity<T extends Model<?>> extends Model<T> {
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 最后修改人
     */
    private Integer modifier;

    /**
     * 逻辑删除标识
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted = 0;
}
