package com.bihell.dice.model.dto;

import com.bihell.dice.model.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文章信息Dto,用于一些列表页
 *
 * @author bihell
 * @since 2018/8/28 14:34
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ArticleInfoDto extends BaseEntity {
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
