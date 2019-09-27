package com.bihell.dice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.exception.NotFoundException;
import com.bihell.dice.exception.TipException;
import com.bihell.dice.mapper.MediaMapper;
import com.bihell.dice.model.domain.Media;
import com.bihell.dice.service.MediaService;
import com.bihell.dice.utils.DiceConsts;
import com.bihell.dice.utils.DiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author haseochen
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MediaServiceImpl implements MediaService {

    private final MediaMapper mediaMapper;

    /**
     * 分页获取媒体
     *
     * @param page  第几页
     * @param limit 每页数量
     * @return Page<Media>
     */
    @Override
    public IPage<Media> pageAdminMedias(Integer page, Integer limit) {
        Page<Media> mediaPage = new Page<>(page, limit);
        LambdaQueryWrapper<Media> wrapper = new QueryWrapper<Media>().lambda()
                .orderByDesc(Media::getId);
        return mediaMapper.selectPage(mediaPage, wrapper);
    }

    /**
     * 获取媒体详情
     *
     * @param id id
     * @return Media
     */
    @Override
    public Media getMedia(Integer id) {
        Media media = mediaMapper.selectById(id);
        if (media.getId() == null) {
            throw new TipException("媒体不存在");
        } else {
            return media;
        }
    }

    /**
     * 上传媒体文件
     *
     * @param file 媒体文件
     * @param name 文件名
     * @param path 文件路径
     * @return
     */

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Media upload(MultipartFile file, String name, String path) {
        if (null == file || file.isEmpty()) {
            throw new TipException("上传文件不能为空");
        }
        if (StringUtils.isEmpty(name)) {
            throw new TipException("文件名不能为空");
        }
        if (name.length() > 255) {
            throw new TipException("文件名过长");
        }

        Media media = new Media();
        try {
            Path basePath = Paths.get(path);

            Path fameDir = DiceUtil.getFameDir();
            Path uploadPath = fameDir.resolve(DiceConsts.MEDIA_DIR);

            String suffix = DiceUtil.getFileSuffix(file.getOriginalFilename());

            String mediaName = name.endsWith(suffix) ? name : name + "." + suffix;
            Path mediaUrl = basePath.resolve(mediaName);
            Path mediaPath = uploadPath.resolve(mediaUrl);
            log.info("Uploading media: [{}]", mediaPath);

            if (Files.exists(mediaPath)) {
                throw new TipException("同名文件已经存在!");
            }

            Files.createDirectories(mediaPath.getParent());
            file.transferTo(mediaPath.toFile());

            // 图片资源压缩图片
            if (Objects.requireNonNull(file.getContentType()).contains("image")) {
                String thumbnailName = name.endsWith(suffix) ?
                        DiceUtil.getFileBaseName(name) + DiceConsts.MEDIA_THUMBNAIL_SUFFIX + "." + suffix
                        : name + DiceConsts.MEDIA_THUMBNAIL_SUFFIX + "." + suffix;

                Path thumbnailUrl = basePath.resolve(thumbnailName);
                Path thumbnailPath = uploadPath.resolve(thumbnailUrl);
                log.info("Compress media thumbnail: [{}]", thumbnailPath);

                DiceUtil.compressImage(mediaPath.toFile(), thumbnailPath.toFile(), 0.5f);
                media.setThumbUrl(thumbnailUrl.toString());
            }

            media.setName(mediaName);
            media.setUrl(mediaUrl.toString());
            media.setSuffix(suffix);
            media.insert();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new TipException(e);
        }
        return media;
    }

    /**
     * 删除媒体
     *
     * @param id 媒体id
     * @return 删除成功
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Integer id) {

        Media media = mediaMapper.selectById(id);
        if (media.getId() == null) {
            throw new NotFoundException(Media.class);
        } else {
            mediaMapper.deleteById(id);
        }

        Path diceDir = DiceUtil.getFameDir();
        Path uploadPath = diceDir.resolve(DiceConsts.MEDIA_DIR);

        Path mediaPath = uploadPath.resolve(media.getUrl());
        if (Files.exists(mediaPath)) {
            try {
                Files.delete(mediaPath);
            } catch (IOException e) {
                throw new TipException(e);
            }
        }

        if (!StringUtils.isEmpty(media.getThumbUrl())) {
            Path thumbnailPath = uploadPath.resolve(media.getThumbUrl());
            if (Files.exists(thumbnailPath)) {
                try {
                    Files.delete(thumbnailPath);
                } catch (IOException e) {
                    throw new TipException(e);
                }
            }
        }
    }
}
