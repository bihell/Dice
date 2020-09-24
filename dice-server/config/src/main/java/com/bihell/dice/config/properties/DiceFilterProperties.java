package com.bihell.dice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * Filter配置属性 todo
 **/
@Data
@Component
@ConfigurationProperties(prefix = "dice.filter")
public class DiceFilterProperties {

    /**
     * RequestDetail Filter配置
     */
    @NestedConfigurationProperty
    private FilterConfig request = new FilterConfig();

    /**
     * XSS Filter配置
     */
    @NestedConfigurationProperty
    private FilterConfig xss = new FilterConfig();

    @Data
    public static class FilterConfig {

        /**
         * 是否启用
         */
        private boolean enable;

        /**
         * 过滤的路径
         */
        private String[] urlPatterns;

        /**
         * 排序
         */
        private int order;

        /**
         * 是否支持异步
         */
        private boolean async;

    }
}
