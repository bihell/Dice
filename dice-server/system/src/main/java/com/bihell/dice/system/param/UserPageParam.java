package com.bihell.dice.system.param;

import com.bihell.dice.framework.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author haseochen
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserPageParam对象", description = "用户列表参数")
public class UserPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 7437881671144580610L;

    @ApiModelProperty("部门id")
    private Long deptId;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("状态，0：禁用，1：启用，2：锁定")
    private Integer status;

    @ApiModelProperty("创建时间开始")
    private Date createTimeStart;

    @ApiModelProperty("创建时间结束")
    private Date createTimeEnd;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;
}
