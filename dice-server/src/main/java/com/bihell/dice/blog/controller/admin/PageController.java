package com.bihell.dice.blog.controller.admin;

import com.bihell.dice.blog.mapper.blogs.ArticleMapper;
import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.blog.param.ArticlePageParam;
import com.bihell.dice.blog.service.blog.ArticleService;
import com.bihell.dice.commons.api.ApiCode;
import com.bihell.dice.commons.api.ApiResult;
import com.bihell.dice.commons.pagination.Paging;
import com.bihell.dice.commons.utils.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 自定义页面管理 Controller
 *
 * @author bihell
 * @since 2017/10/17 12:28
 */
@RestController
@RequestMapping("/v1/api/admin/page")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PageController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    /**
     * 自定义页面列表
     *
     * @param articlePageParam 页面参数
     * @return ApiResult<Paging < Article>>
     */
    @GetMapping("/getPageList")
    public ApiResult<Paging<Article>> getPageList(@Validated ArticlePageParam articlePageParam) {
        Paging<Article> paging = articleService.getAdminPages(articlePageParam);
        return ApiResult.success(paging);
    }

    /**
     * 获取自定义页面信息
     *
     * @param id 自定义页面id
     * @return {@see Article}
     */
    @GetMapping("{id}")
    public ApiResult<Article> getPage(@PathVariable Integer id) {
        Article page = articleService.getAdminPage(id);
        if (null == page) {
            return ApiResult.fail(ApiCode.NOT_FOUND, null);
        }
        return ApiResult.success(page);
    }

    /**
     * 新建或修改自定义页面
     *
     * @param page 页面实体
     * @return {@see String}
     */
    @PostMapping
    @PreAuthorize("@auth.hasPermission('blog:pages:update')")
    public ApiResult<Integer> savePage(@Valid  @RequestBody Article page) {
        page.setCreator(SecurityUtil.getUser().getUserId());
        Integer pageId = articleService.savePage(page);
        return ApiResult.success(pageId);
    }

    /**
     * 删除自定义页面
     *
     * @param id 自定义页面id
     * @return {@see String}
     */
    @DeleteMapping("{id}")
    @PreAuthorize("@auth.hasPermission('blog:pages:delete')")
    public ApiResult<Boolean> deletePage(@PathVariable Integer id) {
        boolean flag = articleMapper.deleteById(id) > 0;
        return ApiResult.result(flag);
    }
}
