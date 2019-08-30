package com.bihell.dice.controller.admin;

import com.bihell.dice.model.domain.Article;
import com.bihell.dice.service.ArticleService;
import com.bihell.dice.util.RestResponse;
import com.bihell.dice.util.Types;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 代码片段 Controller
 *
 * @author haseochen
 */

@RestController
@RequestMapping("/v1/api/admin/snippet")
public class SnippetController {

    @Resource
    private ArticleService articleService;

    /**
     * 新建或修改文章
     *
     * @param id           代码段说明id
     * @param title        代码段说明标题
     * @param content      代码段说明内容
     * @param tags         代码段说明标签
     * @param status       {@link Types#DRAFT},{@link Types#PUBLISH}
     * @param allowComment 是否允许评论
     * @return {@see RestResponse.ok()}
     */
    @PostMapping
    public RestResponse saveSnippet(@RequestParam(value = "id", required = false) Integer id,
                                    @RequestParam(value = "title") String title,
                                    @RequestParam(value = "content") String content,
                                    @RequestParam(value = "tags") String tags,
                                    @RequestParam(value = "status", defaultValue = Types.PUBLISH) String status,
                                    @RequestParam(value = "allowComment", defaultValue = "false") Boolean allowComment,
                                    @RequestParam(value = "created") Long created,
                                    @RequestParam(value = "modified") Long modified) {
        Article snippet = new Article();
        if (!StringUtils.isEmpty(id)) {
            snippet.setId(id);
        }
        snippet.setTitle(title);
        snippet.setContent(content);
        snippet.setTags(tags);
        snippet.setStatus(status);
        snippet.setAllowComment(allowComment);
        snippet.setCreated(new java.util.Date(created));
        snippet.setModified(new java.util.Date(modified));
        snippet.setAuthorId(1);
        Integer articleId = articleService.saveSnippet(snippet);
        return RestResponse.ok(articleId);
    }
}
