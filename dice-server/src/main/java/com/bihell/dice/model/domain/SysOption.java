package com.bihell.dice.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 设置 Model
 *
 * @author bihell
 * @since 2019-05-20 22:39
 */
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class SysOption extends BaseEntity {

    /**
     * 设置key
     */
    @Column(name = "option_key", columnDefinition = "VARCHAR(100) NOT NULL UNIQUE")
    private String optionKey;

    /**
     * 设置 value
     */
    @Column(name = "option_value", columnDefinition = "VARCHAR(1023) NOT NULL")
    private String optionValue;

    /**
     * 创建时间
     */
    @Column(name = "created", columnDefinition = "TIMESTAMP NOT NULL DEFAULT current_timestamp")
    private Date created;

    /**
     * 修改时间
     */
    @Column(name = "modified", columnDefinition = "TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp")
    private Date modified;
}
