package com.bihell.dice.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <pre>
 * 系统角色 查询结果对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "SysRoleQueryVo对象", description = "系统角色查询参数")
public class SysRoleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色唯一编码")
    private String code;

    @ApiModelProperty("角色类型")
    private Integer type;

    @ApiModelProperty("角色状态，0：禁用，1：启用")
    private Integer state;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("版本")
    private Integer version;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("权限集合")
    private Set<Long> permissions;

}