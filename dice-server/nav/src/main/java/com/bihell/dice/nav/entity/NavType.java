package com.bihell.dice.nav.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;
import com.bihell.dice.framework.core.validator.groups.Update;

/**
 *
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NavType对象")
public class NavType extends Model<NavType> {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @ApiModelProperty("导航类型id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("导航类型名称")
    private String name;

    @ApiModelProperty("创建人")
    private Long creator;

    @ApiModelProperty("修改人")
    private Integer modifier;

    @ApiModelProperty("是否公开：1是，0否")
    private Integer isPublic;

    @NotNull(message = "创建时间不能为空")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @NotNull(message = "更新时间不能为空")
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("类型图标")
    private String icon;

    @ApiModelProperty("状态（1启用，0禁用）")
    private Integer status;

    @ApiModelProperty("父id")
    private Integer parentId;

    @ApiModelProperty("排序")
    private Integer sort;

    private Integer level;

}
