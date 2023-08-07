package com.bihell.dice.blog.controller.admin;

import com.bihell.dice.blog.model.blog.Meta;
import com.bihell.dice.blog.model.dto.MetaDto;
import com.bihell.dice.blog.service.blog.MetaService;
import com.bihell.dice.commons.api.ApiResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 属性(标签和分类)管理 Controller
 *
 * @author bihell
 * @since 2017/8/28 23:16
 */
@RestController
@RequestMapping("/v1/api/admin/meta")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MetaController {

    private final MetaService metaService;

    @GetMapping("/getList")
    public ApiResult<List<MetaDto>> getMetaList(@RequestParam String type,
                                                @RequestParam(required = false) String title,
                                                @RequestParam(required = false) String snippetFileContent) {
        return ApiResult.success(metaService.getMetaDtos(type, title, snippetFileContent));
    }

    /**
     * 根据name删除分类
     * @param meta
     * @return
     */
    @DeleteMapping
    @PreAuthorize("@auth.hasPermission('blog:tag:delete')")
    public ApiResult<Boolean>  deleteMeta(@Valid @RequestBody Meta meta) {
        boolean flag=metaService.deleteMeta(meta.getName(), meta.getType());
        return ApiResult.result(flag);
    }

    /**
     * 添加一个分类
     *
     * @param meta
     * @return
     */
    @PostMapping
    @PreAuthorize("@auth.hasPermission('blog:tag:add')")
    public ApiResult<Boolean> saveMeta(@Valid @RequestBody Meta meta) {
        boolean flag = metaService.saveMeta(meta.getName(), meta.getType());
        return ApiResult.result(flag);
    }

    /**
     * 根据id修改分类
     *
     * @param meta
     * @return
     */
    @PostMapping("{id}")
    @PreAuthorize("@auth.hasPermission('blog:tag:update')")
    public ApiResult<Boolean> updateMeta(@Valid @RequestBody Meta meta) {
        boolean flag = metaService.updateMeta(meta.getId(), meta.getName(), meta.getType());
        return ApiResult.result(flag);
    }
}
