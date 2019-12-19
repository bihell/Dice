package com.bihell.dice.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class AuthItem extends Model<AuthItem> {

    @TableId
    private Integer itemId;

    private String itemName;

    private String itemCode;

    private Integer classesId;
    @TableField(value = "`order`")
    private Integer order;

    private String style;

    /**
     * itemCode是否自动生成
     */
    private Boolean autoFlag;

    private String outerUrl;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private List<Integer> apiIds;

    public String getName() {
        return this.itemName;
    }
}
