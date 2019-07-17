package com.bihell.dice.util;

import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 判空工具类
 *
 * @author tpxcer
 */
public final class EmptyUtils {

    private EmptyUtils() {

    }

    /**
     * 判断集合是否为空 coll->null->true coll-> coll.size() == 0 -> true
     *
     * @param coll
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> coll) {
        return (coll == null || coll.isEmpty());
    }

    /**
     * 判断集合是否不为空
     *
     * @param coll
     * @return
     */
    public static <T> boolean isNotEmpty(Collection<T> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断map是否为空
     *
     * @param map
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判断map是否不为空
     *
     * @param map
     * @return
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    /**
     * 判断一个对象是否为空
     *
     * @param t
     * @return
     */
    public static <T> boolean isEmpty(T t) {
        if (t == null) {
            return true;
        }
        return EmptyUtils.isEmpty(t.toString());
    }

    /**
     * 判断数组是否不为空
     */
    public static <T> boolean isNotEmpty(T[] datas) {
        return !isEmpty(datas);
    }

    /**
     * 判断数组是否不为空
     */
    public static <T> boolean isEmpty(T[] datas) {
        return ObjectUtils.isEmpty(datas);
    }


    /**
     * 判断一个对象是否不为空
     *
     * @param t
     * @return
     */
    public static <T> boolean isNotEmpty(T t) {
        return !isEmpty(t);
    }

    /**
     * 判断多个T是否存在空对象，只判断null不判断空 可用于多参数简化代码： 如： if (parameter1==null || parameter2==null || parameter3==null) 可以简化为： if
     * (EmptyUtils.hasNull(parameter1, parameter2,parameter3))
     *
     * @param datas
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean hasNull(T... datas) {
        for (T t : datas) {
            if (t == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个Map是否存在空对象
     *
     * @param datas
     * @param <K>
     * @param <V>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <K, V> boolean hasEmpty(Map<K, V>... datas) {
        for (Map<K, V> data : datas) {
            if (isEmpty(data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个Collection是否存在空对象
     *
     * @param datas
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> boolean hasEmpty(Collection<T>... datas) {
        for (Collection<T> data : datas) {
            if (isEmpty(data)) {
                return true;
            }
        }
        return false;
    }

}