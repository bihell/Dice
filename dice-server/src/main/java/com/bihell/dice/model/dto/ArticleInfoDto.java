package com.bihell.dice.model.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 文章信息Dto,用于一些列表页
 *
 * @author bihell
 * @since 2018/8/28 14:34
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class ArticleInfoDto extends Model<ArticleInfoDto> {
    @TableId
    private Integer id;
    /**
     * 内容标题
     */
    private String title;
    /**
     * 标签列表
     */
    private String tags;
    /**
     * 文章分类
     */
    private String category;
}
