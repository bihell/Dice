package com.bihell.dice.framework.exception;

/**
 * Tip 提示异常
 *
 * @author bihell
 * @since 2017/7/12 21:29
 */
public class TipException extends DiceException{

    public TipException() {
    }

    public TipException(String message) {
        super(message);
    }

    public TipException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipException(Throwable cause) {
        super(cause);
    }
}
