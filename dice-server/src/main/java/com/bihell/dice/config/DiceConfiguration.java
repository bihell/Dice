package com.bihell.dice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.bihell.dice.cache.InMemoryCacheStore;
import com.bihell.dice.cache.StringCacheStore;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
@MapperScan("com.bihell.dice.mapper")
public class DiceConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public StringCacheStore stringCacheStore() {
        return new InMemoryCacheStore();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
