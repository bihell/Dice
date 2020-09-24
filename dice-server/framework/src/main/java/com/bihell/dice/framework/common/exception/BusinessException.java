package com.bihell.dice.framework.common.exception;

import com.bihell.dice.framework.common.api.ApiCode;

/**
 * 业务异常 todo
 */
public class BusinessException extends DiceException {
	private static final long serialVersionUID = -2303357122330162359L;

	public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public BusinessException(ApiCode apiCode) {
        super(apiCode);
    }

}
