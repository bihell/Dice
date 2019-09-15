package com.bihell.dice.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 文章 Model
 *
 * @author bihell
 * @since 2017/7/8 9:29
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Article extends Model<Article> {

    @TableId
    private Integer id;

    /**
     * 内容标题
     */
    private String title;

    /**
     * 内容生成时间
     */
    private Date created;

    /**
     * 内容修改时间
     */
    private Date modified;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容所属用户id
     */
    private Integer authorId;

    /**
     * 点击量
     */
    private Integer hits;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 文章分类
     */
    private String category;

    /**
     * 文章优先级
     */
    private Integer priority;

    /**
     * 内容状态
     */
    private String status;

    /**
     * 内容类别
     */
    private String type;

    /**
     * 是否允许评论
     */
    private Boolean allowComment;

    /**
     * 评论数量
     */
    private Integer commentCount;
}
