package com.bihell.dice.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 生成的表信息配置
 **/
@Data
@Accessors(chain = true)
public class TableConfig {

    /**
     * 生成的表名称
     */
    private String tableName;

    /**
     * 主键数据库列名称
     */
    private String pkIdName = "id";

}
