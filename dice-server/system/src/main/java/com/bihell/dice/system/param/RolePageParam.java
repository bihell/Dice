package com.bihell.dice.system.param;

import com.bihell.dice.framework.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author haseochen
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserPageParam对象", description = "用户列表参数")
public class RolePageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("搜索词")
    private String criteria;

    @ApiModelProperty("项目")
    private String projectType;

    private Integer userId;

    private Integer roleType;

    private Integer userType;

}
