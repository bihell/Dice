package com.bihell.dice.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.bihell.dice.framework.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author haseochen
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AuthRelRoleItem extends BaseEntity<AuthRelRoleItem> {

    @TableId
    private Integer id;

    private Integer roleId;

    private Integer itemId;
}
