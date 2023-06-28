package com.bihell.dice.framework.exception.user;

/**
 * 用户不存在异常
 *
 * @author Tang
 */
public class UserNotFoundException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -7233037800428172210L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
