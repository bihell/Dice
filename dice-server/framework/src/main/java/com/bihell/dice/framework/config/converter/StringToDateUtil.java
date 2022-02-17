package com.bihell.dice.framework.config.converter;

import com.bihell.dice.config.constant.DatePattern;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <code>
 * <pre>
 * 日期转换器,将请求参数的日期字符串转换成java.util.Date类型
 * 日期格式顺序:
 * 	1.yyyy-MM-dd HH:mm:ss.SSS
 * 	2.yyyy-MM-dd HH:mm:ss
 * 	3.yyyy-MM-dd HH:mm
 * 	4.yyyy-MM-dd HH
 * 	5.yyyy-MM-dd
 * </pre>
 * </code>
 *
 */
public class StringToDateUtil {

    /**
     * 时间戳字符长度，不包含毫秒
     */
    private static final Integer TIMESTAMP_LENGTH = 10;

    /**
     * 日期格式化数组
     */
    private static SimpleDateFormat[] dateFormats = {
            new SimpleDateFormat(DatePattern.YYYY_MM_DD_HH_MM_SS_S),
            new SimpleDateFormat(DatePattern.YYYY_MM_DD_HH_MM_SS),
            new SimpleDateFormat(DatePattern.YYYY_MM_DD_HH_MM),
            new SimpleDateFormat(DatePattern.YYYY_MM_DD_HH),
            new SimpleDateFormat(DatePattern.YYYY_MM_DD),
            new SimpleDateFormat(DatePattern.YYYY_MM),
    };

    /**
     * <code>
     * <pre>
     * 1.如果日期字符串为空,则直接返回空
     * 2.使用格式化组进行格式化,如果解析成功,则直接返回
     * 4.否则,抛出非法参数异常
     * @param source 请求的日期参数
     * @return 解析后的日期类型:java.util.Date
     * @exception IllegalArgumentException 非法参数异常
     * </pre>
     * </code>
     */
    public static Date convert(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        source = source.trim();

        try {
            int timeLength = source.length();
            Long time = Long.parseLong(source);
            if (timeLength == TIMESTAMP_LENGTH) {
                time = time * 1000;
            }
            Date date = new Date(time);
            return date;
        } catch (Exception e) {

        }

        Date date = null;
        boolean flag = false;
        for (DateFormat dateFormat : dateFormats) {
            try {
                date = dateFormat.parse(source);
                flag = true;
                break;
            } catch (ParseException e) {
            }
        }

        if (flag) {
            return date;
        } else {
            throw new IllegalArgumentException("不能解析日期:" + source);
        }

    }
}
