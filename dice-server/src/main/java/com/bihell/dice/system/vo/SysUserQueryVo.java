package com.bihell.dice.system.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 系统用户 查询结果对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@Schema(description = "系统用户查询参数")
public class SysUserQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号码")
    private String phone;

    @Schema(description = "性别，0：女，1：男，默认1")
    private Integer gender;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "remark")
    private String remark;

    @Schema(description = "状态，0：禁用，1：启用，2：锁定")
    private Integer state;

    @Schema(description = "部门id")
    private Long deptId;

    @Schema(description = "角色组")
    private List<Long> roleIds;

    @Schema(description = "逻辑删除，0：未删除，1：已删除")
    private Integer deleted;

    @Schema(description = "版本")
    private Integer version;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "修改时间")
    private Date updateTime;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "角色名称")
    private String roleName;

    private String email;

}