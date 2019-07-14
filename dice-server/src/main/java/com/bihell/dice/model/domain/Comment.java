package com.bihell.dice.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import tk.mybatis.mapper.annotation.Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 评论 Model
 *
 * @author bihell
 * @since 2018/1/19 16:27
 */
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Comment extends BaseEntity {

    /**
     * 所属文章id
     */
    @Column(name = "article_id", columnDefinition = "INT NOT NULL")
    private Integer articleId;

    /**
     * 父评论id
     */
    @Column(name = "p_id", columnDefinition = "INT")
    private Integer pId;

    /**
     * 评论内容
     */
    @Column(name = "content", columnDefinition = "TEXT NOT NULL")
    private String content;

    /**
     * 昵称
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;

    /**
     * 邮箱
     */
    @Column(name = "email", columnDefinition = "VARCHAR(255)")
    private String email;

    /**
     * 网址
     */
    @Column(name = "website", columnDefinition = "VARCHAR(255)")
    private String website;

    /**
     * 评论时间
     */
    @Order("desc")
    @Column(name = "created", columnDefinition = "TIMESTAMP NOT NULL DEFAULT current_timestamp")
    private Date created;

    /**
     * 赞
     */
    @Column(name = "agree", columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer agree;

    /**
     * 踩
     */
    @Column(name = "disagree", columnDefinition = "INT NOT NULL DEFAULT 0")
    private Integer disagree;

    /**
     * 评论ip
     */
    @Column(name = "ip", columnDefinition = "VARCHAR(255)")
    private String ip;

    /**
     * 评论agent
     */
    @Column(name = "agent", columnDefinition = "VARCHAR(255)")
    private String agent;

    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "INT DEFAULT 0 NOT NULL")
    private Integer status;
}
