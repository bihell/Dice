package com.bihell.dice.framework.utils;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 *
 * @author Tang
 */
@Component
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 缓存对象
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 缓存对象
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     */
    public void set(String key, Object value, long timeout) {
        set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 缓存对象
     *
     * @param key      键
     * @param value    值
     * @param timeout  过期时间
     * @param timeUnit 时间颗粒度
     */
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     键
     * @param timeout 超时时间
     * @return 结果
     */
    public boolean expire(String key, long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key      键
     * @param timeout  超时时间
     * @param timeUnit 时间颗粒度
     * @return 结果
     */
    public Boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 获取有效时间
     *
     * @param key 键
     * @return 有效时间
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 判断 key 是否存在
     *
     * @param key 键
     * @return 结果
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取缓存对象。
     *
     * @param key 键
     * @return 缓存对象
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除对象
     *
     * @param key 键
     * @return 结果
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 集合对象
     * @return 结果
     */
    public Boolean delete(Collection<String> collection) {
        var count = redisTemplate.delete(collection);
        return count != null && count > 0;
    }

    /**
     * 缓存 List 数据
     *
     * @param key      键
     * @param dataList 数据集合
     * @return 结果
     */
    public Long setList(String key, List<Object> dataList) {
        var count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的 List 对象
     *
     * @param key 键
     * @return 数据集合
     */
    public List<Object> getList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存 Set
     *
     * @param key     键
     * @param dataSet 数据
     * @return 数据
     */
    public BoundSetOperations<String, Object> setSet(String key, Set<Object> dataSet) {
        var setOperation = redisTemplate.boundSetOps(key);
        setOperation.add(dataSet.toArray());
        return setOperation;
    }

    /**
     * 获得缓存的 Set
     *
     * @param key 键
     * @return 数据集合
     */
    public Set<Object> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存 Map
     *
     * @param key     键
     * @param dataMap 数据
     */
    public void setMap(String key, Map<String, Object> dataMap) {
        redisTemplate.opsForHash().putAll(key, dataMap);
    }

    /**
     * 获得缓存的Map
     *
     * @param key 键
     * @return 数据集合
     */
    public Map<Object, Object> getMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往 Hash 中存入数据
     *
     * @param key     Redis键
     * @param hashKey Hash键
     * @param value   值
     */
    public void setMapValue(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 获取 Hash 中的数据
     *
     * @param key     Redis键
     * @param hashKey Hash键
     * @return Hash中的对象
     */
    public Object getMapValue(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key      Redis键
     * @param hashKeys Hash键集合
     * @return Hash对象集合
     */
    public List<Object> getMultiMapValue(String key, Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 删除Hash中的某条数据
     *
     * @param key  Redis键
     * @param hashKey Hash键
     * @return 是否成功
     */
    public boolean deleteMapValue(String key, String hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey) > 0;
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

}
