package com.bihell.dice.system.exception;

import com.bihell.dice.framework.api.ApiCode;
import com.bihell.dice.framework.exception.DiceException;

/**
 * 验证码校验异常
 */
public class VerificationCodeException extends DiceException {
	private static final long serialVersionUID = -2640690119865434398L;

	public VerificationCodeException(String message) {
        super(message);
    }

    public VerificationCodeException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public VerificationCodeException(ApiCode apiCode) {
        super(apiCode);
    }
}
