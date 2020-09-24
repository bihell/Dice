package com.bihell.dice.blog.exception;

import com.bihell.dice.framework.common.api.ApiCode;
import com.bihell.dice.framework.common.exception.DiceException;

/**
 * 系统登录异常 todo
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
