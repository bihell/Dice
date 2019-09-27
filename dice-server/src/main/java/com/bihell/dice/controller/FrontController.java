package com.bihell.dice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.domain.Comment;
import com.bihell.dice.model.dto.*;
import com.bihell.dice.service.*;
import com.bihell.dice.utils.DiceConsts;
import com.bihell.dice.utils.DiceUtil;
import com.bihell.dice.utils.RestResponse;
import com.bihell.dice.utils.Types;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 博客前台 Controller
 *
 * @author bihell
 * @since 2017/7/15 18:29
 */
@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FrontController extends BaseController {

    private final ArticleService articleService;

    private final MetaService metaService;

    private final CommentService commentService;

    private final EmailService emailService;

    private final OptionService optionService;

    /**
     * 文章列表
     *
     * @param page  第几页
     * @param limit 每页数量
     * @return {@see Pagination<Article>}
     */
    @GetMapping("article")
    public RestResponse home(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer limit) {
        IPage<Article> articles = articleService.getFrontArticles(page, limit);
        return RestResponse.ok(new Pagination<Article>(articles));
    }

    /**
     * 文章内容页
     *
     * @param id 文章id
     * @return {@see Article}
     */
    @GetMapping("article/{id}")
    public RestResponse article(@PathVariable Integer id) {
        Article article = articleService.getFrontArticle(id);
        if (null == article) {
            return this.error404();
        }
        this.updateHits(article.getId(), article.getHits());
        return RestResponse.ok(article);
    }

    /**
     * 点击量添加
     *
     * @param articleId 文章id
     * @param hits      当前点击量
     */
    private void updateHits(Integer articleId, Integer hits) {
        Integer cHits = cacheUtil.getCacheValue(DiceConsts.CACHE_ARTICLE_HITS, articleId, Integer.class);
        cHits = null == cHits ? 1 : cHits + 1;
        if (cHits >= DiceConsts.CACHE_ARTICLE_HITS_SAVE) {
            Article temp = new Article();
            temp.setId(articleId);
            temp.setHits(hits + cHits);
            articleService.updateArticle(temp);
            cacheUtil.putCacheValue(DiceConsts.CACHE_ARTICLE_HITS, articleId, 0);
        } else {
            cacheUtil.putCacheValue(DiceConsts.CACHE_ARTICLE_HITS, articleId, cHits);
        }
    }


    /**
     * 标签页
     *
     * @return {@see List<MetaDto>}
     */
    @GetMapping("tag")
    public RestResponse tag() {
        List<MetaDto> metaDtos = metaService.getPublishMetaDtos(Types.TAG);
        return RestResponse.ok(metaDtos);
    }

    /**
     * 分类页
     *
     * @return {@see List<MetaDto>}
     */
    @GetMapping("/category")
    public RestResponse category() {
        List<MetaDto> metaDtos = metaService.getPublishMetaDtos(Types.CATEGORY);
        return RestResponse.ok(metaDtos);
    }

    /**
     * 归档页
     *
     * @return {@see List<Archive>}
     */
    @GetMapping("archive")
    public RestResponse archive() {
        List<Archive> archives = articleService.getArchives();
        return RestResponse.ok(archives);
    }

    /**
     * 获取自定义页面的列表,根据权重排序
     *
     * @return {@see List<NoteInfo>}
     */
    @GetMapping("page")
    public RestResponse pageList() {
        List<Article> pages = articleService.getFrontPageList();
        return RestResponse.ok(pages);
    }

    /**
     * 自定义页面
     *
     * @param id 页面id
     * @return {@see Article}
     */
    @GetMapping("page/{id}")
    public RestResponse page(@PathVariable Integer id) {
        Article page = articleService.getFrontPage(id);
        if (null == page) {
            return error404();
        }
        return RestResponse.ok(page);
    }

    /**
     * 获取文章的评论
     *
     * @param articleId 文章id
     * @param page      第几页
     * @param limit     每页数量
     * @return {@see Pagination<Comment>}
     */
    @GetMapping("comment")
    public RestResponse getArticleComment(@RequestParam Integer articleId, @RequestParam(required = false, defaultValue = "1") Integer page,
                                          @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer limit) {
        IPage<Comment> comments = commentService.getCommentsByArticleId(page, limit, articleId);
        return RestResponse.ok(new Pagination<Comment>(comments));
    }

    /**
     * 发表评论
     *
     * @param articleId 文章id
     * @param pId       父评论id
     * @param content   评论内容
     * @param name      评论用户名
     * @param email     评论用户email
     * @param website   评论用户网址
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("comment")
    public RestResponse postComment(@RequestParam Integer articleId, @RequestParam(required = false) Integer pId,
                                    @RequestParam String content, @RequestParam String name,
                                    @RequestParam(required = false) String email, @RequestParam(required = false) String website) {
        Comment comments = new Comment();
        comments.setArticleId(articleId);
        comments.setPId(pId);
        comments.setContent(content);
        comments.setName(name);
        comments.setEmail(email);
        comments.setWebsite(website);
        comments.setIp(DiceUtil.getIp());
        comments.setAgent(DiceUtil.getAgent());
        commentService.save(comments);

        //发送邮件提醒
        CommentDto commentDetail = commentService.getCommentDetail(comments.getId());
        emailService.sendEmailToAdmin(commentDetail);
        if (null != commentDetail.getPComment() && !StringUtils.isEmpty(commentDetail.getPComment().getEmail())) {
            emailService.sendEmailToUser(commentDetail, commentDetail.getPComment().getEmail());
        }
        return RestResponse.ok();
    }

    /**
     * 顶或踩评论
     *
     * @param commentId 评论id
     * @param assess    {@link Types#AGREE},{@link Types#DISAGREE}
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("comment/{commentId}/assess")
    public RestResponse assessComment(@PathVariable Integer commentId, @RequestParam String assess) {
        commentService.assessComment(commentId, assess);
        return RestResponse.ok();
    }

    /**
     * 获取前端的设置
     *
     * @return Map
     */
    @GetMapping("option")
    public RestResponse getOption() {
        Map<String, String> map = optionService.getFrontOptionMap();
        return RestResponse.ok(map);
    }
}
