package com.bihell.dice.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * @author haseochen
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteMetoVO implements Serializable {

    private String title;

    private Boolean ignoreAuth;

    private String roles;

    private Boolean ignoreKeepAlive;

    private Boolean affix;

    private String icon;

    private String frameSrc;

    private String transitionName;

    private Boolean hideBreadcrumb;

    private Boolean hideChildrenInMenu;

    private Boolean carryParam;

    private Boolean single;

    private String currentActiveMenu;

    private Boolean hideTab;

    private Boolean hideMenu;

    private Boolean isLink;

}
