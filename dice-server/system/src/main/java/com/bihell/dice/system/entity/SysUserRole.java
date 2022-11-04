package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色表
 * </p>
 * @author haseochen
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole extends Model<SysUserRole> {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户编号")
    private Long userId;

    @ApiModelProperty("角色编号")
    private Long roleId;

}