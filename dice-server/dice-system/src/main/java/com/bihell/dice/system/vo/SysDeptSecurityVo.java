package com.bihell.dice.system.vo;

import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@Schema(description = "部门")
public class SysDeptSecurityVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    private Long deptId;

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
    private LocalDateTime createTime;

    @Schema(description = "修改时间")
    @Null(message = "修改时间不用传")
    private LocalDateTime updateTime;
}
