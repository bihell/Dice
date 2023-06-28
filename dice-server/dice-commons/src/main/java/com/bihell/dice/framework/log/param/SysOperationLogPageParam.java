package com.bihell.dice.framework.log.param;

import com.bihell.dice.framework.core.pagination.BasePageOrderParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 系统操作日志 分页参数对象
 * </pre>
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "系统操作日志分页参数")
public class SysOperationLogPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
