package com.bihell.dice.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.controller.BaseController;
import com.bihell.dice.model.domain.User;
import com.bihell.dice.model.dto.Pagination;
import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.query.ArticleQuery;
import com.bihell.dice.service.ArticleService;
import com.bihell.dice.service.LogService;
import com.bihell.dice.util.DiceConsts;
import com.bihell.dice.util.DiceUtil;
import com.bihell.dice.util.RestResponse;
import com.bihell.dice.util.Types;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 后台文章管理 Controller
 *
 * @author bihell
 * @since 2017/7/11 19:52
 */
@RestController
@RequestMapping("/v1/api/admin/article")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ArticleController extends BaseController {

    private final ArticleService articleService;

    private final LogService logService;

    /**
     * 文章信息列表
     *
     * @param page  第几页
     * @param limit 每页数量
     * @return {@see Pagination<Article>}
     */
    @GetMapping
    public RestResponse index(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer limit, ArticleQuery query) {
        IPage<Article> articles = articleService.getAdminArticles(page, limit, query);
        return RestResponse.ok(new Pagination<Article>(articles));
    }

    /**
     * 单个文章信息
     *
     * @param id 文章id
     * @return {@see Article}
     */
    @GetMapping("{id}")
    public RestResponse showArticle(@PathVariable Integer id) {
        Article article = articleService.getAdminArticle(id);
        if (null == article) {
            return this.error404();
        }
        return RestResponse.ok(article);
    }

    /**
     * 新建或修改文章
     *
     * @param id           文章id
     * @param title        文章标题
     * @param content      文章内容
     * @param tags         文章标签
     * @param category     文章分类
     * @param status       {@link Types#DRAFT},{@link Types#PUBLISH}
     * @param allowComment 是否允许评论
     * @param priority     排序权重
     * @return {@see RestResponse.ok()}
     */
    @PostMapping
    public RestResponse saveArticle(@RequestParam(value = "id", required = false) Integer id,
                                    @RequestParam(value = "title") String title,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "tags") String tags,
                                    @RequestParam(value = "category") String category,
                                    @RequestParam(value = "status", defaultValue = Types.DRAFT) String status,
                                    @RequestParam(value = "allowComment", defaultValue = "false") Boolean allowComment,
                                    @RequestParam(value = "created") Long created,
                                    @RequestParam(value = "priority", defaultValue = "0") Integer priority,
                                    @RequestParam(value = "modified") Long modified) {
        User user = this.user();
        Article article = new Article();
        if (!StringUtils.isEmpty(id)) {
            article.setId(id);
        }
        article.setTitle(title);
        article.setContent(content);
        article.setTags(tags);
        article.setCategory(category);
        article.setStatus(status);
        article.setAllowComment(allowComment);
        article.setAuthorId(user.getId());
        article.setCreated(new java.util.Date(created));
        article.setModified(new java.util.Date(modified));
        article.setPriority(priority);
        Integer articleId = articleService.saveArticle(article);
        return RestResponse.ok(articleId);
    }

    /**
     * 删除文章
     *
     * @param id 文章id
     * @return {@see RestResponse.ok()}
     */
    @DeleteMapping("{id}")
    public RestResponse deleteArticle(@PathVariable Integer id) {
        if (articleService.deleteArticle(id)) {
            logService.save(Types.LOG_ACTION_DELETE, "id:" + id, Types.LOG_MESSAGE_DELETE_ARTICLE, Types.LOG_TYPE_OPERATE, DiceUtil.getIp());
            return RestResponse.ok("删除文章成功");
        } else {
            return RestResponse.fail();
        }
    }

    /**
     * 已发布文章数量
     *
     * @return {@see Integer}
     */
    @GetMapping("count")
    public RestResponse count() {
        return RestResponse.ok(articleService.count());
    }

}
