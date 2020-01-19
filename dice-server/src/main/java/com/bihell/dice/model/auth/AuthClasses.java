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
public class AuthClasses extends BaseEntity<AuthClasses> {

    @TableId(value = "classes_id")
    private Integer id;

    private String classesName;

    private String classesUrl;

    private Integer groupId;

    @TableField(value = "`order`")
    private Integer order;

    private Boolean isDisplay;

    private String style;

    @TableField(exist = false)
    private List<AuthItem> children;

    @TableField(exist = false)
    private String name;

    public String getName() {
        return this.classesName;
    }
}
