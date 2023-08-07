package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author haseochen
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole extends Model<SysUserRole> {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户编号")
    private Long userId;

    @Schema(description = "角色编号")
    private Long roleId;

}