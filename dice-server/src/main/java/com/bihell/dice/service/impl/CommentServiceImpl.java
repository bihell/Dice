package com.bihell.dice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.mapper.ArticleMapper;
import com.bihell.dice.mapper.CommentMapper;
import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.domain.Comment;
import com.bihell.dice.model.dto.CommentDto;
import com.bihell.dice.service.CommentService;
import com.bihell.dice.util.DiceConsts;
import com.bihell.dice.util.DiceUtil;
import com.bihell.dice.util.Types;
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
public class CommentServiceImpl implements CommentService {

    public static final String COMMENT_CACHE_NAME = "comments";

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    @CacheEvict(value = {COMMENT_CACHE_NAME, ArticleServiceImpl.ARTICLE_CACHE_NAME}, allEntries = true, beforeInvocation = true)
    public void save(Comment comment) {
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

        Article article = articleMapper.selectByPrimaryKey(comment.getArticleId());
        if (null == article) {
            throw new TipException("无法查询到对应评论文章");
        }

        commentMapper.insertSelective(comment);

        // 增加文章的评论数
        article.setCommentCount(article.getCommentCount() + 1);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    @Cacheable(value = COMMENT_CACHE_NAME, key = "'article_comments['+#page+':'+#limit+':'+#articleId+']'")
    public Page<Comment> getCommentsByArticleId(Integer page, Integer limit, Integer articleId) {
        Comment record = new Comment();
        record.setArticleId(articleId);
        record.setStatus(Types.COMMENT_STATUS_NORMAL);
        Page<Comment> result = PageHelper.startPage(page, limit).doSelectPage(() -> commentMapper.select(record));
        result.forEach(comments -> {
            String content = DiceUtil.contentTransform(comments.getContent(), false, true);
            comments.setContent(content);
        });

        return result;
    }

    @Override
    public Page<Comment> getAdminComments(Integer page, Integer limit) {
        Comment record = new Comment();
        record.setStatus(Types.COMMENT_STATUS_NORMAL);
        Page<Comment> result = PageHelper.startPage(page, limit).doSelectPage(() -> commentMapper.select(record));
        result.forEach(comments -> {
            String content = DiceUtil.contentTransform(comments.getContent(), false, false);
            comments.setContent(content);
        });

        return result;
    }

    @Override
    @Cacheable(value = COMMENT_CACHE_NAME, key = "'comment_detail['+#id+']'")
    public CommentDto getCommentDetail(Integer id) {
        Comment entity = commentMapper.selectByPrimaryKey(id);
        if (null == entity) {
            return null;
        }
        CommentDto comment = new CommentDto();
        BeanUtils.copyProperties(entity, comment);
        if (null != comment.getPId() && -1 != comment.getPId()) {
            Comment pComment = commentMapper.selectByPrimaryKey(comment.getPId());
            comment.setPComment(pComment);
        }

        Article article = articleMapper.selectByPrimaryKey(comment.getArticleId());
        comment.setArticle(article);
        return comment;
    }


    @Override
    @CacheEvict(value = COMMENT_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public boolean deleteComment(Integer id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        if (null == comment) {
            throw new TipException("不存在该评论");
        }

        // 减去文章中评论数
        Article article = articleMapper.selectByPrimaryKey(comment.getArticleId());
        article.setCommentCount(article.getCommentCount() - 1);
        articleMapper.updateByPrimaryKeySelective(article);

        // 去除子评论中关联
        Comment record = new Comment();
        record.setPId(id);
        Comment childComment = commentMapper.selectOne(record);
        if (null != childComment) {
            childComment.setPId(null);
            commentMapper.updateByPrimaryKey(childComment);
        }
        comment.setStatus(Types.COMMENT_STATUS_DELETE);
        if (commentMapper.updateByPrimaryKeySelective(comment) > 0) {
            log.info("删除评论: {}", comment);
            return true;
        }
        return false;
    }

    @Override
    @CacheEvict(value = COMMENT_CACHE_NAME, allEntries = true, beforeInvocation = true)
    public void assessComment(Integer commentId, String assess) {
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
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
        commentMapper.updateByPrimaryKey(comment);
    }

    @Override
    @Cacheable(value = COMMENT_CACHE_NAME, key = "'comment_count'")
    public Integer count() {
        Comment record = new Comment();
        record.setStatus(Types.COMMENT_STATUS_NORMAL);
        return commentMapper.selectCount(record);
    }

}
