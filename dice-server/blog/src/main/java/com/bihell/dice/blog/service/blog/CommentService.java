package com.bihell.dice.blog.service.blog;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.blog.model.blog.Comment;
import com.bihell.dice.blog.model.dto.CommentDto;
import com.bihell.dice.blog.param.CommentPageParam;
import com.bihell.dice.blog.utils.Types;
import com.bihell.dice.framework.core.pagination.Paging;

/**
 * 评论 Service 接口
 *
 * @author bihell
 * @since 2018/1/19 16:56
 */
public interface CommentService {
    /**
     * 保存评论
     *
     * @param comments 评论entity
     */
    boolean save(Comment comments);

    /**
     * 获取文章下的评论
     *
     * @param limit     每页数量
     * @param articleId 文章id
     * @return Page<Comment>
     */
    IPage<Comment> getCommentsByArticleId(Integer current, Integer limit, Integer articleId);


    /**
     * 获取评论列表
     *
     * @param commentPageParam
     * @return Paging<Comment>
     */
    Paging<Comment> getCommentPageList(CommentPageParam commentPageParam);

    /**
     * 获取评论详情
     *
     * @param id 评论id
     * @return CommentDto
     */
    CommentDto getCommentDetail(Integer id);

    /**
     * 删除评论
     *
     * @param id 评论id
     * @return 删除是否成功
     */
    boolean deleteComment(Integer id);

    /**
     * 顶或踩评论
     *
     * @param commentId 评论给id
     * @param assess    {@link Types#AGREE},{@link Types#DISAGREE}
     */
    void assessComment(Integer commentId, String assess);

    /**
     * 评论数量
     *
     * @return 数量
     */
    int count();
}
