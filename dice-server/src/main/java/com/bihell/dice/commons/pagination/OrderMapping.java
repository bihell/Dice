package com.bihell.dice.commons.pagination;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.bihell.dice.commons.utils.PropertyColumnUtil;
import com.google.common.base.CaseFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 排序列映射
 **/
@Data
@Accessors(chain = true)
public class OrderMapping {

    private boolean underLineMode;

    public OrderMapping() {

    }

    public OrderMapping(boolean underLineMode) {
        this.underLineMode = underLineMode;
    }

    private Map<String, String> map = new ConcurrentHashMap<>();

    public OrderMapping mapping(String property, String column) {
        map.put(property, column);
        return this;
    }

    public OrderMapping mapping(String property, String tablePrefix, String column) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            column = tablePrefix + "." + column;
        }
        map.put(property, column);
        return this;
    }


    public OrderMapping mapping(String property, Class<?> clazz) {
        String column = PropertyColumnUtil.getColumn(clazz, property);
        map.put(property, column);
        return this;
    }

    public OrderMapping mapping(String property, String tablePrefix, Class<?> clazz) {
        String column = PropertyColumnUtil.getColumn(clazz, property);
        mapping(property, tablePrefix, column);
        return this;
    }

    public String getMappingColumn(String property) {
        if (StringUtils.isBlank(property)) {
            return null;
        }
        return map.get(property);
    }

    public void filterOrderItems(List<OrderItem> orderItems) {
        if (CollectionUtils.isEmpty(orderItems)) {
            return;
        }
        // 如果集合不为空，则按照PropertyColumnUtil映射
        if (MapUtils.isNotEmpty(map)) {
            orderItems.forEach(item -> {
                item.setColumn(this.getMappingColumn(item.getColumn()));
            });
        } else if (underLineMode) {
            // 如果开启下划线模式，自动转换成下划线
            orderItems.forEach(item -> {
                String column = item.getColumn();
                if (StringUtils.isNotBlank(column)) {
                    // 驼峰转换成下划线
                    item.setColumn(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, column));
                }
            });
        }
    }

}
