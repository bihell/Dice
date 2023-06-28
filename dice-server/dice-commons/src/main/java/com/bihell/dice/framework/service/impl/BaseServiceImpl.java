package com.bihell.dice.framework.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bihell.dice.framework.service.BaseService;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 公共Service父类
 *
 * @author haseochen
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    /**
     * 实体类型
     */
    private Class<?> entityClass;

    {
        Class<?> clazz = this.getClass();
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) p[1];
        }
    }
}