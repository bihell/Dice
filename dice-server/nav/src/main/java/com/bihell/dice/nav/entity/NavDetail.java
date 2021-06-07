package com.bihell.dice.nav.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bihell.dice.framework.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.bihell.dice.framework.core.validator.groups.Update;

/**
 * 导航明细表
 *
 * @author tpxcer
 * @since 2021-06-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NavDetail对象")
public class NavDetail extends Model<NavDetail> {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @ApiModelProperty("导航明细项id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotNull(message = "创建时间不能为空")
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @NotNull(message = "更新时间不能为空")
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人")
    private Long creator;

    @ApiModelProperty("修改人")
    private Integer modifier;

    @ApiModelProperty("逻辑删除标识（0.未删除,1.已删除）")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("是否公开")
    private Integer isPublic;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("简介")
    private String summary;

    @ApiModelProperty("导航地址名称")
    private String name;

    @ApiModelProperty("类型id")
    private Integer typeId;

    @ApiModelProperty("状态（1启用，0禁用）")
    private Integer status;

}
