package com.bihell.dice.framework.exception.user;

/**
 * 密码不匹配异常
 *
 * @author Tang
 */
public class PasswordMismatchException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -7729485563513614850L;

    public PasswordMismatchException() {
    }

    public PasswordMismatchException(String message) {
        super(message);
    }

    public PasswordMismatchException(Throwable cause) {
        super(cause);
    }

    public PasswordMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
