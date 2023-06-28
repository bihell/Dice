package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <pre>
 * 系统权限
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "菜单权限")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "权限名称")
    private String name;

    @Schema(description = "父id")
    private Long parentId;

    @Schema(description = "路径")
    private String routePath;

    @Schema(description = "唯一编码")
    private String code;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "类型，0：目录，1：菜单，2：按钮")
    @NotNull(message = "类型，0：目录，1：菜单，2：按钮 不能为空")
    private Integer type;

    @Schema(description = "层级，1：第一级，2：第二级，N：第N级")
    @NotNull(message = "层级，1：第一级，2：第二级，N：第N级不能为空")
    private Integer level;

    @Schema(description = "状态，0：禁用，1：启用")
    private Integer status;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "版本")
    @Null(message = "版本不用传")
    @Version
    private Integer version;

    @Schema(description = "创建时间")
    @Null(message = "创建时间不用传")
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    @Null(message = "修改时间不用传")
    private LocalDateTime updateTime;

    @Schema(description = "组件")
    private String component;

    private Integer isShow;
    private Integer keepAlive;
    private Integer isExt;
    private Integer frame;

}
