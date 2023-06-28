package com.bihell.dice.framework.exception.file;

/**
 * 文件大小超过最大限制异常
 *
 * @author Tang
 */
public class MaxFileSizeException extends RuntimeException {

    @java.io.Serial
    private static final long serialVersionUID = 7470786893090015507L;

    public MaxFileSizeException() {
    }

    public MaxFileSizeException(String message) {
        super(message);
    }

    public MaxFileSizeException(Throwable cause) {
        super(cause);
    }

    public MaxFileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

}
