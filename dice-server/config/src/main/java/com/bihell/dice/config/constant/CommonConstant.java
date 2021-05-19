package com.bihell.dice.config.constant;

/**
 * 公共常量
 */
public interface CommonConstant {

    /**
     * 默认页码为1
     */
    Long DEFAULT_PAGE_INDEX = 1L;

    /**
     * 默认页大小为10
     */
    Long DEFAULT_PAGE_SIZE = 10L;

    /**
     * 分页总行数名称
     */
    String PAGE_TOTAL_NAME = "total";

    /**
     * 分页数据列表名称
     */
    String PAGE_RECORDS_NAME = "items";

    /**
     * 分页当前页码名称
     */
    String PAGE_INDEX_NAME = "page";

    /**
     * 分页当前页大小名称
     */
    String PAGE_SIZE_NAME = "pageSize";

    /**
     * 登录token
     */
    String JWT_DEFAULT_TOKEN_NAME = "Authorization";

    /**
     * JWT用户名 todo
     */
    String JWT_USERNAME = "username";

    /**
     * JWT刷新新token响应状态码 todo
     */
    int JWT_REFRESH_TOKEN_CODE = 460;

    /**
     * JWT刷新新token响应状态码， todo
     * Redis中不存在，但jwt未过期，不生成新的token，返回361状态码
     */
    int JWT_INVALID_TOKEN_CODE = 461;

    /**
     * JWT Token默认密钥 todo
     */
    String JWT_DEFAULT_SECRET = "666666";

    /**
     * JWT 默认过期时间，3600L，单位秒 todo
     */
    Long JWT_DEFAULT_EXPIRE_SECOND = 3600L;

    /**
     * 验证码token todo
     */
    String VERIFY_TOKEN = "verifyToken";

    /**
     * 图片 todo
     */
    String IMAGE = "image";

    /**
     * JPEG todo
     */
    String JPEG = "JPEG";

    /**
     * base64前缀 todo
     */
    String BASE64_PREFIX = "data:image/png;base64,";

    /**
     * ..
     */
    String SPOT_SPOT = "..";

    /**
     * ../
     */
    String SPOT_SPOT_BACKSLASH = "../";

    /**
     * 用户浏览器代理 todo
     */
    String USER_AGENT = "User-Agent";

    /**
     * 本机地址IP todo
     */
    String LOCALHOST_IP = "127.0.0.1";
    /**
     * 本机地址名称 todo
     */
    String LOCALHOST_IP_NAME = "本机地址";
    /**
     * 局域网IP todo
     */
    String LAN_IP = "192.168";
    /**
     * 局域网名称 todo
     */
    String LAN_IP_NAME = "局域网";
}
