package com.bihell.dice.model.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 关联标签和文章的中间 Model
 *
 * @author bihell
 * @since 2017/9/17 23:37
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Middle extends BaseEntity {

    @Column(name = "a_id", columnDefinition = "INT NOT NULL")
    private Integer aId;

    @Column(name = "m_id", columnDefinition = "INT NOT NULL")
    private Integer mId;
}
