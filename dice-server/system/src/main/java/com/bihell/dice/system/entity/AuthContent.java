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
public class AuthContent extends BaseEntity<AuthContent> {

    @TableId
    private Integer id;

    private String projectType;

    private String contentType;

    private String contentName;

    private String contentValue;

}
