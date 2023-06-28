package com.bihell.dice.auth.config;

import com.bihell.dice.framework.constant.UploadsPrefix;
import com.bihell.dice.framework.properties.DiceProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc 配置
 *
 * @author Tang
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final DiceProperties diceProperties;

    public WebMvcConfig(DiceProperties diceProperties) {
        this.diceProperties = diceProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(UploadsPrefix.UPLOADS + "/**").addResourceLocations("file:" + diceProperties.getUploadFolder() + "/");
    }

}
