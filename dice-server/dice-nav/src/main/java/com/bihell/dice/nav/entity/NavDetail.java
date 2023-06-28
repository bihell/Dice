package com.bihell.dice.nav.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bihell.dice.framework.core.validator.groups.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 导航明细表
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "NavDetail对象")
public class NavDetail extends Model<NavDetail> {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @Schema(description = "导航明细项id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private Long creator;

    @Schema(description = "修改人")
    private Long modifier;

    @Schema(description = "逻辑删除标识（0.未删除,1.已删除）")
    @TableLogic
    private Integer deleted;

    @Schema(description = "是否公开")
    private Integer isPublic;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "简介")
    private String description;

    @Schema(description = "导航地址名称")
    private String name;

    @Schema(description = "类型id")
    private Integer typeId;

    @Schema(description = "状态（1启用，0禁用）")
    private Integer status;

    @Schema(description = "导航URL")
    private String url;

    @Schema(description = "排序")
    private Integer sort;
}
