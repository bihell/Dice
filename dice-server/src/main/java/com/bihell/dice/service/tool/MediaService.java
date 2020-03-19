package com.bihell.dice.service.tool;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bihell.dice.model.params.QueryParam;
import com.bihell.dice.model.tool.Media;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author haseochen
 */
public interface MediaService {

    /**
     * 分页获取媒体
     *
     * @return Page<Media>
     */
    IPage<Media> getMediaList(QueryParam queryParam);

    /**
     * 获取媒体详情
     * @param id id
     * @return Media
     */
    Media getMedia(Integer id);

    /**
     * 上传媒体文件
     *
     * @param file 媒体文件
     * @param name 文件名
     * @param path 文件路径
     * @return
     */
    Media upload(MultipartFile file, String name, String path);

    /**
     * 删除媒体
     *
     * @param id 媒体id
     * @return 删除成功
     */
    void delete(Integer id);
}
