package com.bihell.dice.blog.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.framework.common.api.ApiCode;
import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.util.LoginUtil;
import com.bihell.dice.blog.mapper.blogs.ArticleMapper;
import com.bihell.dice.blog.model.blog.Article;
import com.bihell.dice.framework.core.pagination.Pagination;
import com.bihell.dice.blog.service.blog.ArticleService;
import com.bihell.dice.config.constant.DiceConsts;
import com.bihell.dice.framework.common.api.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param pageNum  第几页
     * @param pageSize 每页数量
     * @return {@see Pagination<Article>}
     */
    @GetMapping
    public RestResponse index(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer pageSize) {
        IPage<Article> pages = articleService.getAdminPages(pageNum, pageSize);
        return RestResponse.ok(new Pagination<Article>(pages));
    }

    /**
     * 获取自定义页面信息
     *
     * @param id 自定义页面id
     * @return {@see Article}
     */
    @GetMapping("{id}")
    public ApiResult<Article> showPage(@PathVariable Integer id) {
        Article page = articleService.getAdminPage(id);
        if (null == page) {
            return ApiResult.fail(ApiCode.NOT_FOUND, null);
        }
        return ApiResult.ok(page);
    }

    /**
     * 新建或修改自定义页面
     *
     * @param page 页面实体
     * @return {@see String}
     */
    @PostMapping
    public RestResponse savePage(@RequestBody Article page) {
        page.setCreator(LoginUtil.getUserId());
        articleService.savePage(page);
        return RestResponse.ok("保存文章成功");
    }

    /**
     * 删除自定义页面
     *
     * @param id 自定义页面id
     * @return {@see String}
     */
    @DeleteMapping("{id}")
    public RestResponse deletePage(@PathVariable Integer id) {

        if (articleMapper.deleteById(id)>0) {
            return RestResponse.ok("删除自定义页面成功");
        } else {
            return RestResponse.fail();
        }
    }
}
