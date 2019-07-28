package com.bihell.dice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mapper插件Config
 *
 * @author bihell
 * @since 2018/1/19 15:27
 */
@Configuration
@MapperScan("com.bihell.dice.mapper")
public class MapperConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
