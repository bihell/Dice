package com.bihell.dice.framework.exception.status;

/**
 * 已存在异常
 *
 * @author Tang
 */
public class ExistedException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -427412847296329889L;

    public ExistedException() {
    }

    public ExistedException(String message) {
        super(message);
    }

    public ExistedException(Throwable cause) {
        super(cause);
    }

    public ExistedException(String message, Throwable cause) {
        super(message, cause);
    }

}
