package com.bihell.dice.commons.constant;

/**
 * 公共常量
 * @author haseochen
 */
public interface CommonConstant {
    /**
     * 请求的原始字符串
     */
    String REQUEST_PARAM_BODY_STRING = "REQUEST_PARAM_BODY_STRING";
    String PACKAGE_NAME = "com.bihell.dice";

    /**
     * 默认页码为1
     */
    Integer DEFAULT_PAGE_INDEX = 1;

    /**
     * 默认页大小为10
     */
    Integer DEFAULT_PAGE_SIZE = 10;

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
     * 验证码token
     */
    String VERIFY_TOKEN = "verifyToken";

    /**
     * 图片
     */
    String IMAGE = "image";

    /**
     * JPEG
     */
    String JPEG = "JPEG";

    /**
     * base64前缀
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

    String COMMA = ",";

    /**
     * 日志链路ID
     */
    String TRACE_ID = "traceId";

    /**
     * 1000
     */
    int ONE_THOUSAND = 1000;
}
