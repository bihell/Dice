package com.bihell.dice.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户显示名称")
    private String nickname;

    @ApiModelProperty("姓名")
    private String realName;

    @ApiModelProperty("用户创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后登陆时间")
    private LocalDateTime logged;

    @ApiModelProperty("头像")
    private String avatar;

    @TableField(exist = false)
    private List<Integer> roles;

    @ApiModelProperty("盐值")
    private String salt;

    @ApiModelProperty("角色组")
    private List<Long> roleIds;

    @ApiModelProperty("部门id")
    @NotNull(message = "部门id不能为空")
    private Long deptId;

    @ApiModelProperty("手机号码")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态，0：禁用，1：启用，2：锁定")
    private Integer status;

    @ApiModelProperty("修改时间")
    @Null(message = "修改时间不用传")
    private Date updateTime;
}
