package com.bihell.dice.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.model.domain.Media;
import com.bihell.dice.model.dto.Pagination;
import com.bihell.dice.service.MediaService;
import com.bihell.dice.util.DiceConsts;
import com.bihell.dice.util.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author haseochen
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/media")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MediaController {

    private final MediaService mediaService;

    /**
     * 分页获取媒体资源
     *
     * @param page  第几页
     * @param limit 每页数量
     * @return {@see Page<Media>}
     */
    @GetMapping
    public RestResponse index(@RequestParam(required = false, defaultValue = "1") Integer page,
                              @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer limit) {
        IPage<Media> medias = mediaService.pageAdminMedias(page, limit);
        return RestResponse.ok(new Pagination<Media>(medias));
    }

    /**
     * 获取媒体详情
     *
     * @param id id
     * @return Media
     */
    @GetMapping("{id}")
    public RestResponse detail(@PathVariable Integer id) {
        Media media = mediaService.getMedia(id);
        return RestResponse.ok(media);
    }

    /**
     * 上传媒体文件
     *
     * @param file 文件
     * @param name 文件名
     * @param path 文件路径
     * @return {@see Media}
     */
    @PostMapping("upload")
    public RestResponse upload(@RequestPart("file") MultipartFile file,
                               @RequestParam String name,
                               @RequestParam String path, HttpServletResponse response) {
        log.info("name:{}, path:{}", name, path);
        Media media = mediaService.upload(file, name, path);
        return RestResponse.ok(media);
    }

    /**
     * 删除媒体文件
     *
     * @param id 媒体id
     * @return {@see RestResponse.ok()}
     */
    @DeleteMapping("{id}")
    public RestResponse delete(@PathVariable Integer id) {
        mediaService.delete(id);
        return RestResponse.ok();
    }
}
