package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author haseochen
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AuthRole extends Model<AuthRole> {

    @TableId(value = "role_id")
    private Integer id;

    private String roleName;

    /**
     * 1 管理员
     * 2 普通用户
     */
    private String roleType;

    private Integer userType;

    private String projectType;

    private String description;

    /**
     * 当前角色的有效用户数
     */
    @TableField(exist = false)
    private Integer userCount;

    @TableField(exist = false)
    private List<Integer> apiIds;

    @TableField(exist = false)
    private List<Integer> itemIds;

    @TableField(exist = false)
    private List<Integer> contentIds;

    @TableField(exist = false)
    private List<Integer> userIds;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 最后修改人
     */
    private Integer modifier;

    /**
     * 逻辑删除标识
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted = 0;
}
