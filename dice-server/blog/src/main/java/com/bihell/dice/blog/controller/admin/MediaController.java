package com.bihell.dice.blog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.blog.mapper.tool.MediaMapper;
import com.bihell.dice.framework.core.pagination.Pagination;
import com.bihell.dice.blog.model.params.QueryParam;
import com.bihell.dice.blog.model.tool.Media;
import com.bihell.dice.blog.service.tool.MediaService;
import com.bihell.dice.framework.common.api.RestResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * @author haseochen
 */
@Slf4j
@RestController
@RequestMapping("/v1/api/admin/media")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MediaController {

    private final MediaService mediaService;
    private final MediaMapper mediaMapper;

    /**
     * 分页获取媒体资源
     *
     * @return {@see Page<Media>}
     */
    @GetMapping
    public RestResponse index(QueryParam queryParam) {
        IPage<Media> medias = mediaService.getMediaList(queryParam);
        return RestResponse.ok(new Pagination<Media>(medias));
    }

    @GetMapping("media_types")
    @ApiOperation("Lists all of media types")
    public RestResponse listMediaTypes() {
        return RestResponse.ok(mediaMapper.selectList(new QueryWrapper<Media>()
                .lambda().select(Media::getSuffix).groupBy(Media::getSuffix))
                .stream().map(Media::getSuffix).collect(Collectors.toList()));
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
