package com.bihell.dice.blog.model.blog;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 设置 Model
 *
 * @author bihell
 * @since 2019-05-20 22:39
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysOption extends Model<SysOption> {

    @TableId
    private Integer id;

    /**
     * 设置key
     */
    private String optionKey;

    /**
     * 设置 value
     */
    private String optionValue;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 修改时间
     */
    private LocalDateTime modified;
}
