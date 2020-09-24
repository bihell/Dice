package com.bihell.dice.framework.shiro.exception;


import com.bihell.dice.framework.common.api.ApiCode;
import com.bihell.dice.framework.common.exception.DiceException;

/**
 * Shiro配置异常 todo
 **/
public class ShiroConfigException extends DiceException {
	private static final long serialVersionUID = -4573955712491628431L;

	public ShiroConfigException(String message) {
        super(message);
    }

    public ShiroConfigException(Integer errorCode, String message) {
        super(errorCode, message);
    }

    public ShiroConfigException(ApiCode apiCode) {
        super(apiCode);
    }
}
