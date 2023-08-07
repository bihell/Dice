package com.bihell.dice.commons.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色VO
 *
 * @author Kevin
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleInfoVO implements Serializable {

    private String roleName;

    private String value;

    private Long id;

}
