package com.bihell.dice.nav.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bihell.dice.commons.core.validator.groups.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 *
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "NavType对象")
public class NavType extends Model<NavType> {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "导航类型id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "导航类型名称")
    private String name;

    @Schema(description = "创建人")
    private Long creator;

    @Schema(description = "修改人")
    private Long modifier;

    @Schema(description = "是否公开：1是，0否")
    private Integer isPublic;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "类型图标")
    private String icon;

    @Schema(description = "状态（1启用，0禁用）")
    private Integer status;

    @Schema(description = "父id")
    private Integer parentId;

    @Schema(description = "排序")
    private Integer sort;

    private Integer level;

}
