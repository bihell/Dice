package com.bihell.dice.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MP 自动填充
 *
 * @author haseochen
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object val = getFieldValByName("updateTime", metaObject);
        if (val == null) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
