package com.bihell.dice.framework.exception.user;

/**
 * 非法登陆类型异常
 *
 * @author Tang
 */
public class IllegalLoginTypeException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -313603368031449927L;

    public IllegalLoginTypeException() {
    }

    public IllegalLoginTypeException(String message) {
        super(message);
    }

    public IllegalLoginTypeException(Throwable cause) {
        super(cause);
    }

    public IllegalLoginTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
