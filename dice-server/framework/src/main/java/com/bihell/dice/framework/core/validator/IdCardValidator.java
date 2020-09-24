package com.bihell.dice.framework.core.validator;

import com.bihell.dice.framework.core.validator.constraints.IdCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 自定义身份证号码验证注解实现类 todo
 */
public class IdCardValidator implements ConstraintValidator<IdCard, String> {
	private static final String REG_EX = "(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{2}[0-9Xx]$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}[0-9Xx]$)";
	private static final Pattern PATTERN = Pattern.compile(REG_EX);

	@Override
	public void initialize(IdCard parameters) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		if (value ==null){
			return true;
		}
		return PATTERN.matcher(value).matches();
	}
}
