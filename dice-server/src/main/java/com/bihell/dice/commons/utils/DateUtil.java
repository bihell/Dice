package com.bihell.dice.commons.utils;


import com.bihell.dice.commons.constant.DatePattern;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author haseochen
 */
public class DateUtil {

    public static String toYMDhms(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter formater = DateTimeFormatter.ofPattern(DatePattern.YYYY_MM_DD_HH_MM_SS);
        return formater.format(localDateTime);
    }

    public static Long toTs(String YmDHms) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern(DatePattern.YYYY_MM_DD_HH_MM_SS);
        LocalDateTime localDateTime = LocalDateTime.parse(YmDHms, formater);
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return LocalDateTime.ofInstant(
                dateToConvert.toInstant(), ZoneId.systemDefault());
    }

}
