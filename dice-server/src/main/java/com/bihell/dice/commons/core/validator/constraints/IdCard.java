package com.bihell.dice.commons.core.validator.constraints;

import com.bihell.dice.commons.core.validator.IdCardValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义身份证号码正则验证注解
 */
@Documented
@Constraint(validatedBy = { IdCardValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface IdCard {
	String message() default "请输入有效的身份证号码";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
