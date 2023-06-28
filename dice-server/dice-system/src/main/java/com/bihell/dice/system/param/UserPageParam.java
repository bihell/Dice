package com.bihell.dice.system.param;

import com.bihell.dice.framework.core.pagination.BasePageOrderParam;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "用户列表参数")
public class UserPageParam extends BasePageOrderParam {

    private static final long serialVersionUID = 7437881671144580610L;

    @Schema(description = "部门id")
    private Long deptId;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "状态，0：禁用，1：启用，2：锁定")
    private Integer status;

    @Schema(description = "创建时间开始")
    private Date createTimeStart;

    @Schema(description = "创建时间结束")
    private Date createTimeEnd;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;
}
