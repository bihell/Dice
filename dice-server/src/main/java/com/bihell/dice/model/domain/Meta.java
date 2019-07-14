package com.bihell.dice.model.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 属性(分类和标签) Model
 *
 * @author bihell
 * @since 2017/8/28 23:04
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Meta extends BaseEntity {

    /**
     * 属性名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    /**
     * 属性类型
     */
    @Column(name = "type", columnDefinition = "VARCHAR(45) NOT NULL")
    private String type;
}
