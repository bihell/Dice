package com.bihell.dice.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS跨域配置
 *
 * @author Tang
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 1.添加 CORS配置信息
        var corsConfiguration = new CorsConfiguration();
        // 放行哪些原始域
        corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL);
        // 放行哪些请求方式
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        // 放行哪些请求头
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 暴露哪些头部信息
        corsConfiguration.addExposedHeader(CorsConfiguration.ALL);
        // 是否发送 Cookie
        corsConfiguration.setAllowCredentials(true);
        // 2. 添加映射路径
        var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
        // 3. 返回新的CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }

}
