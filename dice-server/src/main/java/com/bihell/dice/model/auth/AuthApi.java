package com.bihell.dice.model.auth;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bihell.dice.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haseochen
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class AuthApi extends BaseEntity<AuthApi> {

    @TableId(value = "api_id")
    private Integer id;

    private String apiType;

    private String apiPath;

    private String projectType;

    @TableField(exist = false)
    private List<String> apiPaths=new ArrayList<String>();
}
