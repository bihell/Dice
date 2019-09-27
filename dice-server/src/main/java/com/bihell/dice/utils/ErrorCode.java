package com.bihell.dice.utils;

/**
 * 错误返回信息 Enum
 *
 * @author bihell
 * @since 2017/8/30 16:00
 */
public enum ErrorCode {
    /**
     * 运行时异常
     */
    RUNTIME(1000, "RuntimeException"),
    /**
     * 空指针异常
     */
    NULL_POINTER(1001, "NullPointerException "),
    /**
     * 类型住转换异常
     */
    CLASS_CAST(1002, "ClassCastException"),
    /**
     * IO异常
     */
    IO(1003, "IOException"),
    /**
     * 找不到方法异常
     */
    NO_SUCH_METHOD(1004, "NoSuchMethodException"),
    /**
     * 数组越界异常
     */
    INDEX_OUTOF_BOUNDS(1005, "IndexOutOfBoundsException"),
    /**
     * 400异常
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * 404异常
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * 方法不允许异常
     */
    METHOD_BOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * 不可到达异常
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    /**
     * 500异常
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * 用户未登陆
     */
    NOT_LOGIN(999, "Not Login"),
    /**
     * 无 Token
     */
    TOKEN_ERROR(999, "Token Expired or Not Exist");

    private final int code;
    private final String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
