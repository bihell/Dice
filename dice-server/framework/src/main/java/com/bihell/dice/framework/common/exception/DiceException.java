package com.bihell.dice.framework.common.exception;

import com.bihell.dice.framework.common.api.ApiCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DiceException extends RuntimeException{

    private static final long serialVersionUID = -2470461654663264392L;

    private Integer errorCode;
    private String message;

    public DiceException() {
        super();
    }

    public DiceException(String message) {
        super(message);
        this.message = message;
    }

    public DiceException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public DiceException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.errorCode = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public DiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiceException(Throwable cause) {
        super(cause);
    }

}
