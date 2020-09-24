package com.bihell.dice.framework.config.converter;

import org.springframework.core.convert.converter.Converter;

/**
todo
 */
public class StringToDoubleConverter implements Converter<String, Double> {

	@Override
	public Double convert(String source) {
		return StringToDoubleUtil.convert(source);
	}
}
