package com.bihell.dice.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;


/**
 * @author haseochen
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    private final RedisTemplate redisTemplate;

    @Autowired
    public RedisConfig(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        //RedisSerializer<Object> jsonString = new GenericToStringSerializer<>(Object.class);
        RedisSerializer<Object> jsonString = new FastJsonRedisSerializer<>(Object.class);
        // String 的 key 和 hash 的 key 都采用 String 的序列化方式
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        // value 都采用 fastjson 的序列化方式
        redisTemplate.setValueSerializer(jsonString);
        redisTemplate.setHashValueSerializer(jsonString);
        return redisTemplate;
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}