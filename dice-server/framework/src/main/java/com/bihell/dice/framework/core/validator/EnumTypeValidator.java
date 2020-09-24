package com.bihell.dice.framework.core.validator;

import com.bihell.dice.framework.common.enums.BaseEnum;
import com.bihell.dice.framework.common.exception.BusinessException;
import com.bihell.dice.framework.core.validator.constraints.EnumType;
import com.bihell.dice.framework.util.BaseEnumUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义系统内的枚举验证注解实现类 todo
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
