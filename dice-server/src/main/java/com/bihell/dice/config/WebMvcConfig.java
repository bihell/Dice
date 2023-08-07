package com.bihell.dice.config;

import com.bihell.dice.commons.constant.UploadsPrefix;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.config.properties.XssProperties;
import com.bihell.dice.framework.filter.JsonRequestBodyFilter;
import com.bihell.dice.framework.filter.TraceIdLogFilter;
import com.bihell.dice.framework.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WebMvc 配置
 *
 * @author Tang
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final DiceProperties diceProperties;

    private final XssProperties xssProperties;

    public WebMvcConfig(DiceProperties diceProperties, XssProperties xssProperties) {
        this.diceProperties = diceProperties;
        this.xssProperties = xssProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(UploadsPrefix.UPLOADS + "/**").addResourceLocations("file:" + diceProperties.getUploadFolder() + "/");
    }

    @Bean
    public FilterRegistrationBean traceIdLogFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TraceIdLogFilter traceIdLogFilter = new TraceIdLogFilter();
        filterRegistrationBean.setFilter(traceIdLogFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean jsonRequestBodyFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        JsonRequestBodyFilter jsonRequestBodyFilter = new JsonRequestBodyFilter();
        filterRegistrationBean.setFilter(jsonRequestBodyFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

    /**
     * XssFilter配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setEnabled(xssProperties.isEnable());
        filterRegistrationBean.addUrlPatterns(xssProperties.getUrlPatterns());
        filterRegistrationBean.setOrder(xssProperties.getOrder());
        filterRegistrationBean.setAsyncSupported(xssProperties.isAsync());
        return filterRegistrationBean;
    }
}
