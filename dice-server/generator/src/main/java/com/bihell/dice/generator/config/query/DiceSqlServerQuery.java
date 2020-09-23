package com.bihell.dice.generator.config.query;

import com.baomidou.mybatisplus.generator.config.querys.SqlServerQuery;

/**
 * SQLServer自定义生成器查询SQL
 **/
public class DiceSqlServerQuery extends SqlServerQuery {

    /**
     * fix mybatisplus3.3.1 SQLServer SQL过滤表，TABLE_NAME报错问题
     * @return
     */
    @Override
    public String tablesSql() {
        return  "select TABLE_NAME,COMMENTS from (" +
                "   select cast(so.name as varchar(500)) as TABLE_NAME, " +
                "   cast(sep.value as varchar(500)) as COMMENTS from sysobjects so " +
                "   left JOIN sys.extended_properties sep on sep.major_id=so.id and sep.minor_id=0 " +
                "   where (xtype='U' or xtype='v')" +
                ") tb " +
                "where 1 = 1 ";

    }
}
