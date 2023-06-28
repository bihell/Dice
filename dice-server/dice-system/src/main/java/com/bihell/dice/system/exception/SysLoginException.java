package com.bihell.dice.system.exception;

import com.bihell.dice.framework.api.ApiCode;
import com.bihell.dice.framework.exception.DiceException;

/**
 * 系统登录异常
 */
public class SysLoginException extends DiceException {
	private static final long serialVersionUID = -3157438982569715170L;

	public SysLoginException(String message) {
        super(message);
    }

    public SysLoginException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public SysLoginException(ApiCode apiCode) {
        super(apiCode);
    }
}
