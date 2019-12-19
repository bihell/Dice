package com.bihell.dice.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class AuthClasses extends Model<AuthClasses> {

    @TableId
    private Integer classesId;

    private String classesName;

    private String classesUrl;

    private Integer groupId;

    private Integer order;

    private Boolean isDisplay;

    private String style;

    private List<AuthItem> children;

    private String name;

    public String getName() {
        return this.classesName;
    }
}
