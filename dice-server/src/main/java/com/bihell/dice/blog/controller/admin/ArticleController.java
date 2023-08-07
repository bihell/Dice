package com.bihell.dice.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.blog.param.ArticlePageParam;
import com.bihell.dice.blog.service.blog.ArticleService;
import com.bihell.dice.blog.utils.Types;
import com.bihell.dice.commons.api.ApiCode;
import com.bihell.dice.commons.api.ApiResult;
import com.bihell.dice.commons.api.RestResponse;
import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.commons.log.annotation.OperationLog;
import com.bihell.dice.commons.log.enums.OperationLogType;
import com.bihell.dice.commons.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 文章分页列表
     */
    @PreAuthorize("@auth.hasPermission('blog:posts:page')")
    @GetMapping("/getPageList")
    @OperationLog(name = "文章分页列表", type = OperationLogType.PAGE)
    @Operation(summary = "文章分页列表")
    public ApiResult<Paging<Article>> getArticlePageList(@Validated ArticlePageParam articlePageParam) throws Exception {
        Paging<Article> paging = articleService.getArticlePageList(articlePageParam);
        return ApiResult.success(paging);
    }

    /**
     * 单个文章信息
     *
     * @param id 文章id
     * @return {@see Article}
     */
    @GetMapping("{id}")
    public ApiResult<Article> getArticle(@PathVariable Integer id) {
        Article article = articleService.getAdminArticle(id);
        if (null == article) {
            return ApiResult.fail(ApiCode.NOT_FOUND, null);
        }
        return ApiResult.success(article);
    }

    /**
     * 新建或修改文章
     */
    @PostMapping
    @PreAuthorize("@auth.hasPermission('blog:posts:update')")
    public ApiResult<Integer> saveArticle(@Valid @RequestBody Article article) {
        article.setCreator(SecurityUtil.getUser().getUserId());
        Integer articleId = articleService.saveArticle(article);
        return ApiResult.success(articleId);
    }

    /**
     * 删除文章
     * @param id 文章id
     * @return ApiResult<Boolean>
     */
    @DeleteMapping("{id}")
    @PreAuthorize("@auth.hasPermission('blog:posts:delete')")
    public ApiResult<Boolean> deleteArticle(@PathVariable Integer id) {
        boolean flag = articleService.deleteArticle(id);
        return ApiResult.result(flag);
    }

    /**
     * 已发布文章数量
     *
     * @return {@see Integer}
     */
    @GetMapping("count")
    public RestResponse count() {
        return RestResponse.ok(articleService.count(new QueryWrapper<Article>().lambda()
                .eq(Article::getType, Types.POST)
        ));
    }
}
