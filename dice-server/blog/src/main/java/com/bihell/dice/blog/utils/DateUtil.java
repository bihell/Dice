package com.bihell.dice.blog.utils;

import com.bihell.dice.framework.common.exception.TipException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author haseochen
 */
public class DateUtil {

    /**
     * 日期转化
     *
     * @param dateInString 需要转换日期的字符串
     * @return 转换后的日期
     */
    public static Date getDateFromString(String dateInString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return formatter.parse(dateInString);
        } catch (Exception e) {
            throw new TipException("日期转换错误：" + e);
        }
    }

    @Deprecated
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    @Deprecated
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaSqlTimestamp(Date dateToConvert) {
        return new java.sql.Timestamp(
                dateToConvert.getTime()).toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeVisLong(Long timeStamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
    }

    public static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(
                dateToConvert.toInstant(), ZoneId.systemDefault());
    }
}
