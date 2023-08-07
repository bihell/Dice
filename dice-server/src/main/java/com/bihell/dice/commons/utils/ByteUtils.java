package com.bihell.dice.commons.utils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 字节工具类
 *
 * @author Tang
 */
public class ByteUtils {

    private ByteUtils() {
    }

    /**
     * 格式化为带单位的字符串
     *
     * @param size 字节大小
     * @return 带单位的字符串
     */
    public static String getSize(long size) {
        if (size <= 0) {
            return "0 B";
        }
        var sizeUnits = List.of("B", "KB", "MB", "GB", "TB", "PB", "EB");
        var formatter = new DecimalFormat("#,##0.#");
        var digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return formatter.format(size / Math.pow(1024, digitGroups)) + " " + sizeUnits.get(digitGroups);
    }

}
