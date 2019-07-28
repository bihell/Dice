package com.bihell.dice.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 关联标签和文章的中间 Model
 *
 * @author bihell
 * @since 2017/9/17 23:37
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Middle extends Model<Middle> {

    @TableId
    private Integer id;

    private Integer aId;

    private Integer mId;
}
