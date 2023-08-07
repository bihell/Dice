package com.bihell.dice.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysUserDto extends Model<SysUserDto> {
    @TableId
    private Long id;

    @Schema(description = "账号")
    private String username;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "用户邮箱")
    private String email;

    @Schema(description = "用户显示名称")
    private String nickname;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "用户创建时间")
    private LocalDateTime createTime;

    @Schema(description = "最后登陆时间")
    private LocalDateTime logged;

    @Schema(description = "头像")
    private String avatar;

    @TableField(exist = false)
    private List<Integer> roles;

    @Schema(description = "角色组")
    private List<Long> roleIds;

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
}
