package com.bihell.dice.commons.api;

import com.alibaba.fastjson2.annotation.JSONField;
import com.bihell.dice.commons.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * REST API 返回结果
 *
 * @author geekidea
 * @since 2022-3-16
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@Schema(description = "响应结果")
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = 7594052194764993562L;

    @Schema(description = "响应编码 200：成功，500：失败")
    private int code;

    @Schema(description = "响应结果 true：成功，false：失败")
    private boolean success;

    @Schema(description = "响应消息")
    private String message;

    @Schema(description = "响应结果数据")
    private T result;

    @Schema(description = "响应时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @Schema(description = "日志链路ID")
    private String traceId;

    public static ApiResult success() {
        return success(null);
    }

    public static ApiResult success(Object data) {
        return result(ApiCode.SUCCESS, data);
    }

    public static ApiResult fail() {
        return fail(ApiCode.FAIL);
    }

    public static ApiResult fail(String message) {
        return fail(ApiCode.FAIL, message);
    }

    public static ApiResult fail(ApiCode apiCode) {
        return fail(apiCode, null);
    }

    public static ApiResult fail(ApiCode apiCode, String message) {
        if (ApiCode.SUCCESS == apiCode) {
            throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
        }
        return result(apiCode, message, null);
    }

    public static ApiResult result(boolean flag) {
        if (flag) {
            return success();
        }
        return fail();
    }

    public static ApiResult result(ApiCode apiCode) {
        return result(apiCode, null);
    }

    public static ApiResult result(ApiCode apiCode, Object data) {
        return result(apiCode, null, data);
    }

    public static ApiResult<Map<String, Object>> okMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(key, value);
        return success(map);
    }

    public static ApiResult result(ApiCode apiCode, String message, Object data) {
        if (apiCode == null) {
            throw new RuntimeException("结果状态码不能为空");
        }
        boolean success = false;
        int code = apiCode.getCode();
        if (ApiCode.SUCCESS.getCode() == code) {
            success = true;
        }
        String outMessage;
        if (StringUtils.isBlank(message)) {
            outMessage = apiCode.getMessage();
        } else {
            outMessage = message;
        }
        String traceId = MDC.get(CommonConstant.TRACE_ID);
        return ApiResult.builder()
                .code(code)
                .message(outMessage)
                .result(data)
                .success(success)
                .time(new Date())
                .traceId(traceId)
                .build();
    }

}
