package com.bihell.dice.framework.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * todo
 */
@Component
@Slf4j
public class RedisCacheUtil {

    private static RedisCacheUtil redisCacheUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 将当前对象赋值给静态对象,调用spring组件: redisCacheUtil.redisTemplate.xxx()
     */
    @PostConstruct
    public void init(){
        redisCacheUtil = this;
    }
}
