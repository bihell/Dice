package com.bihell.dice.mapper;

import com.bihell.dice.util.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haseochen
 */
public class MetaSql {
    private static final Logger log = LoggerFactory.getLogger(MetaSql.class);

    public String selectMetaDtos(String type) {
        StringBuilder sql = new StringBuilder(
                "select t1.id, t1.name, t1.type, count(t3.id) as count\n" +
                "from dice.meta t1\n" +
                "left join dice.middle t2 on t2.m_id = t1.id " +
                "left join dice.article t3 on t3.id = t2.a_id ");
        switch (type) {
            case Types.SNIPPET_TAG:
                sql.append(" and t3.type='" + Types.SNIPPET + "'");
                break;
            case Types.TAG:
                sql.append(" and t3.type='" + Types.POST + "'");
                break;
            default:
        }
        sql.append(" and t3.status != '" + Types.DELETE + "'");
        sql.append(" where 1=1 ");
        sql.append(" and t1.type = #{type} ");
        sql.append(" group by t1.id, t1.name, t1.type");

        log.info("获取属性以及属性下的文章 sql is :" + sql);
        return sql.toString();
    }
}
