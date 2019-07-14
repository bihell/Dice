package com.bihell.dice.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tk.mybatis.mapper.annotation.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 文章 Model
 *
 * @author bihell
 * @since 2017/7/8 9:29
 */
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Article extends BaseEntity {

    /**
     * 内容标题
     */
    @Column(name = "title", columnDefinition = "VARCHAR(255) NOT NULL")
    private String title;

    /**
     * 内容生成时间
     */
    @Order("desc")
    @Column(name = "created", columnDefinition = "TIMESTAMP NOT NULL DEFAULT current_timestamp")
    private Date created;

    /**
     * 内容修改时间
     */
    @Column(name = "modified", columnDefinition = "TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp")
    private Date modified;

    /**
     * 内容
     */
    @Column(name = "content", columnDefinition = "MEDIUMTEXT")
    private String content;

    /**
     * 内容所属用户id
     */
    @Column(name = "author_id", columnDefinition = "INT")
    private Integer authorId;

    /**
     * 点击量
     */
    @Column(name = "hits", columnDefinition = "INT DEFAULT 0 NOT NULL")
    private Integer hits;

    /**
     * 标签列表
     */
    @Column(name = "tags", columnDefinition = "VARCHAR(500)")
    private String tags;

    /**
     * 文章分类
     */
    @Column(name = "category", columnDefinition = "VARCHAR(500)")
    private String category;

    /**
     * 内容状态
     */
    @Column(name = "status", columnDefinition = "VARCHAR(32)")
    private String status;

    /**
     * 内容类别
     */
    @Column(name = "type", columnDefinition = "VARCHAR(32)")
    private String type;

    /**
     * 是否允许评论
     */
    @Column(name = "allow_comment", columnDefinition = "BOOLEAN DEFAULT TRUE NOT NULL")
    private Boolean allowComment;

    /**
     * 评论数量
     */
    @Column(name = "comment_count", columnDefinition = "INT DEFAULT 0 NOT NULL")
    private Integer commentCount;
}
