package com.bihell.dice.framework.exception.status;

/**
 * 已删除异常
 *
 * @author Tang
 */
public class DeletedException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = 2138002629245398538L;

    public DeletedException() {
    }

    public DeletedException(String message) {
        super(message);
    }

    public DeletedException(Throwable cause) {
        super(cause);
    }

    public DeletedException(String message, Throwable cause) {
        super(message, cause);
    }

}
