package com.bihell.dice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * 缓存工具类
 *
 * @author bihell
 * @since 2019/3/25 11:32
 */
@Component
public class CacheUtil {

    @Autowired
    private CacheManager cacheManager;

    /**
     * 获取缓存
     *
     * @param cacheName 缓存名
     * @return
     */
    public Cache getCache(String cacheName) {
        return cacheManager.getCache(cacheName);
    }

    /**
     * 存放缓存值
     *
     * @param cacheName 缓存名
     * @param key       key
     * @param value     value
     */
    public void putCacheValue(String cacheName, Object key, Object value) {
        getCache(cacheName).put(key, value);
    }

    /**
     * 获取缓存值
     *
     * @param cacheName 缓存名
     * @param key       key
     * @param type      值类型
     * @param <T>       值类型
     * @return 缓存值
     */
    public <T> T getCacheValue(String cacheName, Object key, Class<T> type) {
        return getCache(cacheName).get(key, type);
    }


}
