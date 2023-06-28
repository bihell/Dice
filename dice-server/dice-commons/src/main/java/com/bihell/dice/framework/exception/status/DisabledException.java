package com.bihell.dice.framework.exception.status;

/**
 * 已停用异常
 *
 * @author Tang
 */
public class DisabledException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = -2366984149402556499L;

    public DisabledException() {
    }

    public DisabledException(String message) {
        super(message);
    }

    public DisabledException(Throwable cause) {
        super(cause);
    }

    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }

}
