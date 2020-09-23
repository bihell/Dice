package com.bihell.dice.generator.exception;


import com.bihell.dice.framework.common.api.ApiCode;
import com.bihell.dice.framework.common.exception.DiceException;

/**
 * 代码生成异常
 */
public class GeneratorException extends DiceException {
	private static final long serialVersionUID = 2556853577480934761L;

	public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public GeneratorException(ApiCode apiCode) {
        super(apiCode);
    }
}
