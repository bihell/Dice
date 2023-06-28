package com.bihell.dice.annotaion;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.bihell.dice.DiceApplication;
import com.bihell.dice.auth.config.CorsConfig;
import com.bihell.dice.auth.config.RedisConfig;
import com.bihell.dice.config.MybatisPlusConfig;
import com.bihell.dice.config.SwaggerConfig;
import com.bihell.dice.framework.utils.SpringUtils;
import org.junit.jupiter.api.Test;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.CorsFilter;


@SpringBootTest
public class AnnotationTests {
    @Test
    public void returnBean(){
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MybatisPlusConfig.class);
//        MybatisPlusInterceptor bean = applicationContext.getBean(MybatisPlusInterceptor.class);
//        System.out.println(bean);
//
//
//        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(CorsConfig.class);
//        CorsFilter bean1 = applicationContext1.getBean(CorsFilter.class);
//        System.out.println(bean1);

        String[] beanDefinitionNames = SpringUtils.getApplicationContext().getBeanDefinitionNames();
        for (String str :
                beanDefinitionNames) {
            System.out.println(str);
        }

        Object springUtils = SpringUtils.getBean("redisTemplate");
        System.out.println(springUtils);

        RedisTemplate bean = SpringUtils.getBean("redisTemplate", RedisTemplate.class);
        System.out.println(bean);
    }

}
