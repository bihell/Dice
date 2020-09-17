package com.bihell.dice.blog.model.tool;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author haseochen
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Media extends Model<Media> {

    /**
     * Id
     */
    @TableId
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 修改时间
     */
    private LocalDateTime modified;

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件url
     */
    private String url;

    /**
     * 缩略图url
     */
    private String thumbUrl;

    /**
     * 后缀
     */
    private String suffix;
}
