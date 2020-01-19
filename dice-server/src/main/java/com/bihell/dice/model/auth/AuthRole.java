package com.bihell.dice.model.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bihell.dice.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author haseochen
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AuthRole extends BaseEntity<AuthRole> {

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

}
