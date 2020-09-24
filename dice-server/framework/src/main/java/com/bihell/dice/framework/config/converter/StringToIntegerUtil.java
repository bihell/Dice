package com.bihell.dice.framework.config.converter;

import org.apache.commons.lang3.StringUtils;

/**
 * <code>
 * <pre>
 * 空字符串("")转换成Integer的null todo
 *
 * </pre>
 * </code>
 */
public class StringToIntegerUtil {

	public static Integer convert(String source) {
		if (StringUtils.isBlank(source)){
			return null;
		}
		Integer i = Integer.parseInt(source);
		return i;
	}
}
