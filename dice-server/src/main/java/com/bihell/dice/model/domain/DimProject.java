package com.bihell.dice.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class DimProject extends Model<DimProject> {
    @TableId
    private Integer id;
    /**
     * 产品类型
     */
    private String type;
    /**
     * 产品名
     */
    private String typeName;
    private Integer isDisplay;
    private String style;
    private String domain;

}
