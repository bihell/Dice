package com.bihell.dice.config;

import com.alibaba.fastjson.JSON;
import com.bihell.dice.system.interceptor.DownloadInterceptor;
import com.bihell.dice.system.interceptor.ResourceInterceptor;
import com.bihell.dice.system.interceptor.UploadInterceptor;
import com.bihell.dice.config.properties.DiceFilterProperties;
import com.bihell.dice.config.properties.DiceInterceptorProperties;
import com.bihell.dice.config.properties.DiceProperties;
import com.bihell.dice.framework.core.filter.RequestDetailFilter;
import com.bihell.dice.framework.core.interceptor.PermissionInterceptor;
import com.bihell.dice.framework.core.xss.XssFilter;
import com.bihell.dice.framework.util.IniUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * WebMvc配置 todo
 */
@Slf4j
@Configuration
public class DiceWebMvcConfig implements WebMvcConfigurer {

    /**
     * dice配置属性
     */
    @Autowired
    private DiceProperties diceProperties;

    /**
     * Filter配置
     */
    private DiceFilterProperties filterConfig;

    /**
     * 拦截器配置
     */
    private DiceInterceptorProperties interceptorConfig;

    /**
     * RequestDetailFilter配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean requestDetailFilter() {
        DiceFilterProperties.FilterConfig requestFilterConfig = filterConfig.getRequest();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestDetailFilter());
        filterRegistrationBean.setEnabled(requestFilterConfig.isEnable());
        filterRegistrationBean.addUrlPatterns(requestFilterConfig.getUrlPatterns());
        filterRegistrationBean.setOrder(requestFilterConfig.getOrder());
        filterRegistrationBean.setAsyncSupported(requestFilterConfig.isAsync());
        return filterRegistrationBean;
    }

    /**
     * XssFilter配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilter() {
        DiceFilterProperties.FilterConfig xssFilterConfig = filterConfig.getXss();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setEnabled(xssFilterConfig.isEnable());
        filterRegistrationBean.addUrlPatterns(xssFilterConfig.getUrlPatterns());
        filterRegistrationBean.setOrder(xssFilterConfig.getOrder());
        filterRegistrationBean.setAsyncSupported(xssFilterConfig.isAsync());
        return filterRegistrationBean;
    }


    /**
     * 自定义权限拦截器
     *
     * @return
     */
    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }

    /**
     * 上传拦截器
     *
     * @return
     */
    @Bean
    public UploadInterceptor uploadInterceptor() {
        return new UploadInterceptor();
    }

    /**
     * 资源拦截器
     *
     * @return
     */
    @Bean
    public ResourceInterceptor resourceInterceptor() {
        return new ResourceInterceptor();
    }

    /**
     * 下载拦截器
     *
     * @return
     */
    @Bean
    public DownloadInterceptor downloadInterceptor() {
        return new DownloadInterceptor();
    }


    @PostConstruct
    public void init() {
        filterConfig = diceProperties.getFilter();
        interceptorConfig = diceProperties.getInterceptor();
        // 打印DiceProperties配置信息
        log.debug("DiceProperties：{}", JSON.toJSONString(diceProperties));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 上传拦截器
        if (interceptorConfig.getUpload().isEnable()) {
            registry.addInterceptor(uploadInterceptor())
                    .addPathPatterns(interceptorConfig.getUpload().getIncludePaths());
        }

        // 资源拦截器注册
        if (interceptorConfig.getResource().isEnable()) {
            registry.addInterceptor(resourceInterceptor())
                    .addPathPatterns(interceptorConfig.getResource().getIncludePaths());
        }

        // 下载拦截器注册
        if (interceptorConfig.getDownload().isEnable()) {
            registry.addInterceptor(downloadInterceptor())
                    .addPathPatterns(interceptorConfig.getDownload().getIncludePaths());
        }

        // 自定义权限拦截器注册
        if (interceptorConfig.getPermission().isEnable()) {
            registry.addInterceptor(permissionInterceptor())
                    .addPathPatterns(interceptorConfig.getPermission().getIncludePaths())
                    .excludePathPatterns(interceptorConfig.getPermission().getExcludePaths());
        }

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置项目静态资源访问
        String resourceHandlers = diceProperties.getResourceHandlers();
        if (StringUtils.isNotBlank(resourceHandlers)) {
            Map<String, String> map = IniUtil.parseIni(resourceHandlers);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String pathPatterns = entry.getKey();
                String resourceLocations = entry.getValue();
                registry.addResourceHandler(pathPatterns)
                        .addResourceLocations(resourceLocations);
            }
        }

        // 设置上传文件访问路径 todo
        registry.addResourceHandler(diceProperties.getResourceAccessPatterns())
                .addResourceLocations("file:" + diceProperties.getUploadFolder());
    }

}
