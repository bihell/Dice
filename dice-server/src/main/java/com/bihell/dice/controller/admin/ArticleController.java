package com.bihell.dice.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.controller.BaseController;
import com.bihell.dice.model.dto.Pagination;
import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.params.ArticleParam;
import com.bihell.dice.service.ArticleService;
import com.bihell.dice.utils.DiceConsts;
import com.bihell.dice.utils.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 文章信息列表
     *
     * @param pageNum  第几页
     * @param pageSize 每页数量
     * @return {@see Pagination<Article>}
     */
    @GetMapping
    public RestResponse index(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer pageSize, Article query) {
        IPage<Article> articles = articleService.getAdminArticles(pageNum, pageSize, query);
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
     */
    @PostMapping
    public RestResponse saveArticle(@RequestBody ArticleParam articleParam) {
        articleParam.setAuthorId(this.user().getId());
        Integer articleId = articleService.saveArticle(articleParam);
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
