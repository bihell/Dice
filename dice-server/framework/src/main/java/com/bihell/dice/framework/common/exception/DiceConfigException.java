package com.bihell.dice.framework.common.exception;

import com.bihell.dice.framework.common.api.ApiCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Dice配置异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DiceConfigException extends DiceException {

    private static final long serialVersionUID = 8952028631871769425L;

    private Integer errorCode;
    private String message;

    public DiceConfigException() {
        super();
    }

    public DiceConfigException(String message) {
        super(message);
        this.message = message;
    }

    public DiceConfigException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public DiceConfigException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.errorCode = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public DiceConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiceConfigException(Throwable cause) {
        super(cause);
    }

}
