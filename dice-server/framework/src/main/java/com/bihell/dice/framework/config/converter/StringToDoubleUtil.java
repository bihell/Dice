package com.bihell.dice.framework.config.converter;


import org.apache.commons.lang3.StringUtils;

/**
 * <code>
 * <pre>
 * 空字符串("")转换成Double的null todo
 *
 * </pre>
 * </code>
 */
public class StringToDoubleUtil {

	public static Double convert(String source) {
		if (StringUtils.isBlank(source)){
			return null;
		}
		Double d = Double.parseDouble(source);
		return d;
	}
}
