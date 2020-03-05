package com.bihell.dice.model.blog;

import com.baomidou.mybatisplus.annotation.TableId;
import com.bihell.dice.model.BaseEntity;
import com.bihell.dice.enums.PostStatusEnum;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 文章 Model
 *
 * @author bihell
 * @since 2017/7/8 9:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Article extends BaseEntity<Article> {

    @TableId
    private Integer id;
    private String title;
    private String content;
    private Integer hits;
    private String tags;
    private String category;
    private Integer priority;
    private PostStatusEnum status;
    private String type;
    private Boolean allowComment;
    private Integer commentCount;
}
