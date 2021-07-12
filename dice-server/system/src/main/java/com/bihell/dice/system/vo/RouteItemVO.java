package com.bihell.dice.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;


/**
 * @author haseochen
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RouteItemVO implements Serializable {

    private String path;

    private String component;

    private RouteMetoVO meta;

    private String name;

    private String alias;

    private String redirect;

    private Boolean caseSensitive;

    private List<RouteItemVO> children;

}
