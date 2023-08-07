package com.bihell.dice.system.param;

import com.bihell.dice.commons.pagination.BasePageOrderParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author haseochen
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "用户列表参数")
public class ApiPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @Schema(description = "搜索词")
    private String criteria;

    @Schema(description = "项目")
    private String projectType;

    private Integer roleId;

    private Integer itemId;
}
