package com.bihell.dice.annotaion;

import com.bihell.dice.commons.utils.SpringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


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

        String[] beanDefinitionNames = SpringUtil.getApplicationContext().getBeanDefinitionNames();
        for (String str :
                beanDefinitionNames) {
            System.out.println(str);
        }

        Object springUtils = SpringUtil.getBean("redisTemplate");
        System.out.println(springUtils);

        RedisTemplate bean = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
        System.out.println(bean);
    }

}
