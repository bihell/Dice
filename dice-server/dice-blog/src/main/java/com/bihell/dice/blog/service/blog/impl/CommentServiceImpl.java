package com.bihell.dice.blog.service.blog.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.blog.mapper.blogs.CommentMapper;
import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.blog.model.blog.Comment;
import com.bihell.dice.blog.model.dto.CommentDto;
import com.bihell.dice.blog.param.CommentPageParam;
import com.bihell.dice.framework.exception.TipException;
import com.bihell.dice.blog.service.blog.CommentService;
import com.bihell.dice.framework.constant.DiceConsts;
import com.bihell.dice.framework.service.impl.BaseServiceImpl;
import com.bihell.dice.framework.core.pagination.PageInfo;
import com.bihell.dice.framework.core.pagination.Paging;
import com.bihell.dice.framework.utils.DiceUtil;
import com.bihell.dice.blog.utils.Types;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 评论 Service 实现类
 *
 * @author bihell
 * @since 2018/1/19 16:57
 */
@Slf4j
@Service("commentsService")
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommentServiceImpl extends BaseServiceImpl<CommentMapper, Comment> implements CommentService {

    private static final String COMMENT_CACHE_NAME = "comments";

    private final CommentMapper commentMapper;

    /**
     * 保存评论
     *
     * @param comment 评论entity
     * @return
     */
    @Override
    @CacheEvict(value = {COMMENT_CACHE_NAME, ArticleServiceImpl.ARTICLE_CACHE_NAME}, allEntries = true, beforeInvocation = true)
    public boolean save(Comment comment) {
        if (null == comment) {
            throw new TipException("评论对象为空");
        }
        if (StringUtils.isEmpty(comment.getContent())) {
            throw new TipException("评论不能为空");
        }
        if (comment.getContent().length() > DiceConsts.MAX_COMMENT_CONTENT_COUNT) {
            throw new TipException("评论字数不能超过" + DiceConsts.MAX_COMMENT_CONTENT_COUNT);
        }
        if (StringUtils.isEmpty(comment.getName())) {
            throw new TipException("名称不能为空");
        }
        if (comment.getName().length() > DiceConsts.MAX_COMMENT_NAME_COUNT) {
            throw new TipException("名称字数不能超过" + DiceConsts.MAX_COMMENT_NAME_COUNT);
        }
        if (!StringUtils.isEmpty(comment.getEmail()) && comment.getEmail().length() > DiceConsts.MAX_COMMENT_EMAIL_COUNT) {
            throw new TipException("邮箱字数不能超过" + DiceConsts.MAX_COMMENT_EMAIL_COUNT);
        }
        if (!StringUtils.isEmpty(comment.getWebsite()) && comment.getWebsite().length() > DiceConsts.MAX_COMMENT_WEBSITE_COUNT) {
            throw new TipException("网址长度不能超过" + DiceConsts.MAX_COMMENT_WEBSITE_COUNT);
        }

        Article article = new Article().selectOne(new QueryWrapper<Article>().lambda().eq(Article::getId, comment.getArticleId()));
        if (null == article) {
            throw new TipException("无法查询到对应评论文章");
        }

        comment.insert();

        // 增加文章的评论数
        article.setCommentCount(article.getCommentCount() + 1);
        article.updateById();
        return false;
    }

    /**
     * 获取文章下的评论
     *
     * @param current   第几页
     * @param limit     每页数量
     * @param articleId 文章id
     * @return Page<Comment>
     */
    @Override
    @Cacheable(value = COMMENT_CACHE_NAME, key = "'article_comments['+#current+':'+#limit+':'+#articleId+']'")
    public IPage<Comment> getCommentsByArticleId(Integer current, Integer limit, Integer articleId) {

        Page<Comment> page = new Page<>(current, limit);

        LambdaQueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().lambda()
                .eq(Comment::getArticleId, articleId)
                .eq(Comment::getStatus, Types.COMMENT_STATUS_NORMAL);
        IPage<Comment> result = commentMapper.selectPage(page, wrapper);

        result.getRecords().forEach(comments -> {
            String content = DiceUtil.contentTransform(comments.getContent(), false, true);
            comments.setContent(content);
        });

        return result;
    }

    @Override
    public Paging<Comment> getCommentPageList(CommentPageParam commentPageParam) {
        Page<Comment> page = new PageInfo<>(commentPageParam, OrderItem.desc("create_time"));

        LambdaQueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().lambda()
                .eq(Comment::getStatus, Types.COMMENT_STATUS_NORMAL);

        IPage<Comment> result = commentMapper.selectPage(page, wrapper);
        result.getRecords().forEach(comments -> {
            String content = DiceUtil.contentTransform(comments.getContent(), false, true);
            comments.setContent(content);
        });
        return new Paging<>(result);
    }

    /**
     * 获取评论详情
     *
     * @param id 评论id
     * @return CommentDto
     */
    @Override
    @Cacheable(value = COMMENT_CACHE_NAME, key = "'comment_detail['+#id+']'")
    public CommentDto getCommentDetail(Integer id) {
        Comment entity = new Comment().selectById(id);
        if (null == entity) {
            return null;
        }
        CommentDto comment = new CommentDto();
        BeanUtils.copyProperties(entity, comment);
        if (null != comment.getPId() && -1 != comment.getPId()) {
            Comment pComment = new Comment().selectOne(new QueryWrapper<Comment>().lambda().eq(Comment::getPId, comment.getPId()));
            comment.setPComment(pComment);
        }

        Article article = new Article().selectById(comment.getArticleId());
        comment.setArticle(article);
        return comment;
    }

    /**
     * 删除评论
     *
     * @param id 评论id
     * @return 删除是否成功
     */
    @Override
    @CacheEvict(value = COMMENT_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public boolean deleteComment(Integer id) {
        Comment comment = new Comment().selectById(id);
        if (null == comment) {
            throw new TipException("不存在该评论");
        }

        // 减去文章中评论数
        Article article = new Article().selectById(comment.getArticleId());
        article.setCommentCount(article.getCommentCount() - 1);
        article.updateById();

        // 去除子评论中关联
        Comment childComment = new Comment().selectById(id);
        if (null != childComment) {
            childComment.setPId(null);
            childComment.updateById();
        }
        comment.setStatus(Types.COMMENT_STATUS_DELETE);
        if (comment.updateById()) {
            log.info("删除评论: {}", comment);
            return true;
        }
        return false;
    }

    /**
     * 顶或踩评论
     *
     * @param commentId 评论给id
     * @param assess    {@link Types#AGREE},{@link Types#DISAGREE}
     */
    @Override
    @CacheEvict(value = COMMENT_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public void assessComment(Integer commentId, String assess) {
        Comment comment = new Comment().selectById(commentId);
        if (null == comment) {
            throw new TipException("没有该评论");
        }

        if (Types.AGREE.equals(assess)) {
            comment.setAgree(comment.getAgree() + 1);
        } else if (Types.DISAGREE.equals(assess)) {
            comment.setDisagree(comment.getDisagree() + 1);
        } else {
            throw new TipException("assess参数错误");
        }
        comment.updateById();
    }

    /**
     * 评论数量
     *
     * @return 数量
     */
    @Override
    @Cacheable(value = COMMENT_CACHE_NAME, key = "'comment_count'")
    public long count() {
        return commentMapper.selectCount(new QueryWrapper<Comment>().lambda().eq(Comment::getStatus, Types.COMMENT_STATUS_NORMAL));
    }

}
