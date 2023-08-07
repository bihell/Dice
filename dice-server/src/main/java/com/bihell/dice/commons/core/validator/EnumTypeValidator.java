package com.bihell.dice.commons.core.validator;

import com.bihell.dice.commons.core.validator.constraints.EnumType;
import com.bihell.dice.commons.enums.BaseEnum;
import com.bihell.dice.commons.exception.BusinessException;
import com.bihell.dice.commons.utils.BaseEnumUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 自定义系统内的枚举验证注解实现类
 */
public class EnumTypeValidator implements ConstraintValidator<EnumType, Integer> {

    private Class<? extends BaseEnum> baseEnum;

    @Override
    public void initialize(EnumType parameters) {
        baseEnum = parameters.type();
        if (baseEnum == null) {
            throw new BusinessException("请传入枚举类型类");
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return BaseEnumUtil.exists(baseEnum, value);
    }
}
