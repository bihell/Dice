package com.bihell.dice.framework.config.converter;

import org.springframework.core.convert.converter.Converter;

/**
 * <code>
 * todo
 * </code>
 */
public class StringToIntegerConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String source) {
		return StringToIntegerUtil.convert(source);
	}
}
