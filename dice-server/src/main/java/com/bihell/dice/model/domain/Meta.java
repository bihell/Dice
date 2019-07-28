package com.bihell.dice.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * 属性(分类和标签) Model
 *
 * @author bihell
 * @since 2017/8/28 23:04
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Meta extends Model<Meta> {
    @TableId
    private Integer id;

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性类型
     */
    private String type;
}
