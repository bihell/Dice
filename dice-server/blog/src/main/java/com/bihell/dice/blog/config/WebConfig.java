package com.bihell.dice.blog.config;

import com.bihell.dice.config.constant.DiceConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置
 *
 * @author bihell
 * @since 2017/12/19 11:11
 */
@Slf4j
@Configuration
public class WebConfig {

    private static final String FILE_PROTOCOL = "file:///";

    private static final String MEDIA_PATH_PATTERNS = "/media/**";

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            // 资源
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                String mediaResource = FILE_PROTOCOL + DiceConsts.USER_HOME + DiceConsts.DICE_HOME + DiceConsts.MEDIA_DIR;
                log.info("MediaResource:{}", mediaResource);

                registry.addResourceHandler(MEDIA_PATH_PATTERNS)
                        .addResourceLocations(mediaResource);
            }
        };
    }
}
