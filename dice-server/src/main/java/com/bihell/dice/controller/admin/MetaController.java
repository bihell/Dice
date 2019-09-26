package com.bihell.dice.controller.admin;

import com.bihell.dice.controller.BaseController;
import com.bihell.dice.service.MetaService;
import com.bihell.dice.utils.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 属性(标签和分类)管理 Controller
 *
 * @author bihell
 * @since 2017/8/28 23:16
 */
@RestController
@RequestMapping("/v1/api/admin/meta")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MetaController extends BaseController {

    private final MetaService metaService;

    /**
     * 获取所有属性
     *
     * @return {@see List<MetaDto>}
     */
    @GetMapping
    public RestResponse getAll(@RequestParam String type,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) String snippetFileContent) {
        return RestResponse.ok(metaService.getMetaDtos(type, title, snippetFileContent));
    }

    /**
     * 根据name删除分类
     *
     * @param name 属性名
     * @param type 属性类型 {@see Types#CATEGORY},{@see Types#TAG}
     * @return {@see RestResponse.ok()}
     */
    @DeleteMapping
    public RestResponse deleteMeta(@RequestParam String name, @RequestParam String type) {
        if (metaService.deleteMeta(name, type)) {
            return RestResponse.ok();
        }
        return RestResponse.fail();
    }

    /**
     * 添加一个分类
     *
     * @param name 属性名
     * @param type 属性类型 {@see Types#CATEGORY},{@see Types#TAG}
     * @return {@see RestResponse.ok()}
     */
    @PostMapping
    public RestResponse saveMeta(@RequestParam String name, @RequestParam String type) {
        if (metaService.saveMeta(name, type)) {
            return RestResponse.ok();
        }
        return RestResponse.fail();
    }

    /**
     * 根据id修改分类
     *
     * @param id   属性id
     * @param name 新属性名
     * @param type 新属性类型
     * @return
     */
    @PostMapping("{id}")
    public RestResponse updateMeta(@PathVariable Integer id, @RequestParam String name, @RequestParam String type) {
        if (metaService.updateMeta(id, name, type)) {
            return RestResponse.ok();
        }
        return RestResponse.fail();
    }
}
