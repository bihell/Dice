package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <pre>
 * 部门
 * </pre>
 *
 * @author geekidea
 * @since 2019-10-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "部门")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "部门名称")
    @NotBlank(message = "部门名称不能为空")
    private String deptName;

    @Schema(description = "父id")
    private Long parentId;

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
    private Date createTime;

    @Schema(description = "修改时间")
    @Null(message = "修改时间不用传")
    private Date updateTime;

}
