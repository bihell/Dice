package com.bihell.dice.service;

import com.bihell.dice.blog.service.tool.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangbowen
 * @since 2019/7/9 17:58
 */
@Slf4j
public class MediaServiceTests extends BaseTests {

    @Autowired
    private MediaService mediaService;

    @Test
    public void pageAdminMedias() {
//        System.out.println(mediaService.getMediaList(1, 20));
    }
}
