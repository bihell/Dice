package com.bihell.dice.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.controller.BaseController;
import com.bihell.dice.model.domain.Article;
import com.bihell.dice.model.dto.Pagination;
import com.bihell.dice.model.domain.User;
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
 * 自定义页面管理 Controller
 *
 * @author bihell
 * @since 2017/10/17 12:28
 */
@RestController
@RequestMapping("/v1/api/admin/page")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PageController extends BaseController {

    private final ArticleService articleService;

    private final LogService logService;

    /**
     * 自定义页面列表
     *
     * @param page  第几页
     * @param limit 每页数量
     * @return {@see Pagination<Article>}
     */
    @GetMapping
    public RestResponse index(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer limit) {
        IPage<Article> pages = articleService.getAdminPages(page, limit);
        return RestResponse.ok(new Pagination<Article>(pages));
    }

    /**
     * 获取自定义页面信息
     *
     * @param id 自定义页面id
     * @return {@see Article}
     */
    @GetMapping("{id}")
    public RestResponse showPage(@PathVariable Integer id) {
        Article page = articleService.getAdminPage(id);
        if (null == page) {
            return this.error404();
        }
        return RestResponse.ok(page);
    }

    /**
     * 新建或修改自定义页面
     *
     * @param id      自定义页面id
     * @param title   标题
     * @param content 内容
     * @param status  {@link Types#DRAFT},{@link Types#PUBLISH}
     * @return {@see String}
     */
    @PostMapping
    public RestResponse savePage(@RequestParam(value = "id", required = false) Integer id,
                                 @RequestParam(value = "title") String title,
                                 @RequestParam(value = "content") String content,
                                 @RequestParam(value = "status", defaultValue = Types.DRAFT) String status) {
        User user = this.user();
        Article page = new Article();
        if (!StringUtils.isEmpty(id)) {
            page.setId(id);
        }
        page.setTitle(title);
        page.setContent(content);
        page.setStatus(status);
        page.setAuthorId(user.getId());
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
        if (articleService.deletePage(id)) {
            logService.save(Types.LOG_ACTION_DELETE, "id:" + id, Types.LOG_MESSAGE_DELETE_PAGE, Types.LOG_TYPE_OPERATE, DiceUtil.getIp());
            return RestResponse.ok("删除自定义页面成功");
        } else {
            return RestResponse.fail();
        }
    }
}
