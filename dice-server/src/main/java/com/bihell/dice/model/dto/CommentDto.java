package com.bihell.dice.model.dto;

import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.domain.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 评论 Dto
 *
 * @author bihell
 * @since 2018/1/21 16:08
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class CommentDto extends Comment {

    /**
     * 评论文章
     */
    private Article article;

    /**
     * 父评论
     */
    private Comment pComment;
}
