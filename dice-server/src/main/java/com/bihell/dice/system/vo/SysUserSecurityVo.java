package com.bihell.dice.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 用户 Model
 *
 * @author bihell
 * @since 2017/7/9 22:09
 */

@ToString(callSuper = true)
@Data
@Accessors(chain = true)
public class SysUserSecurityVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId
    private Long userId;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "用户显示名称")
    private String nickname;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "用户创建时间")
    private LocalDateTime createTime;

    @Schema(description = "最后登陆时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate;

    @Schema(description = "头像")
    private String avatar;

    @TableField(exist = false)
    private List<Integer> roles;

    @Schema(description = "部门id")
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    @Schema(description = "手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态，0：禁用，1：启用，2：锁定")
    private Integer status;

    @Schema(description = "修改时间")
    @Null(message = "修改时间不用传")
    private Date updateTime;

    private SysDeptSecurityVo dept;

    private Integer isDeleted;

    private String gender;

    private String loginIp;
}
