package com.bihell.dice.controller.admin;

import com.bihell.dice.controller.BaseController;
import com.bihell.dice.model.dto.Snippet;
import com.bihell.dice.service.SnippetService;
import com.bihell.dice.utils.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 代码片段 Controller
 *
 * @author haseochen
 */

@RestController
@RequestMapping("/v1/api/admin/snippet")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SnippetController extends BaseController {

    private final SnippetService snippetService;

    /**
     * 创建或更新代码段
     *
     * @param snippet 代码段实体
     * @return articleId
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public RestResponse saveSnippet(@RequestBody Snippet snippet) {
        Integer articleId = snippetService.saveSnippet(snippet);
        return RestResponse.ok(articleId);
    }

    /**
     * 删除代码段
     *
     * @param snippetId 代码段id
     * @return {@see RestResponse.ok()}
     */
    @DeleteMapping
    public RestResponse deleteSnippet(@RequestParam(value = "snippetId") Integer snippetId) {
        if (snippetService.deleteSnippet(snippetId)) {
            return RestResponse.ok("删除代码段成功");
        } else {
            return RestResponse.fail();
        }
    }

    /**
     * 根据 id 获取代码段
     *
     * @param snippetId 代码段 id
     * @return Snippet
     */
    @GetMapping
    public RestResponse getSnippetById(@RequestParam(value = "snippetId") Integer snippetId) {
        return RestResponse.ok(snippetService.getSnippetById(snippetId));
    }

    /**
     * 标签页
     *
     * @return {@see List<MetaDto>}
     */
    @GetMapping("snippet_title")
    public RestResponse getSnippetTitle(@RequestParam(value = "metaId") Integer metaId) {
        return RestResponse.ok(snippetService.getSnippetByMeta(metaId));
    }

}
