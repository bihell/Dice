package com.bihell.dice.blog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.bihell.dice.config.DiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Dice configuration.
 *
 * @author johnniang
 */
@Configuration
@EnableConfigurationProperties(DiceProperties.class)
@Slf4j
public class DiceConfiguration {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
