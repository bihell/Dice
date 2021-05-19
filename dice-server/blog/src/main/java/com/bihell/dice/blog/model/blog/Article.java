package com.bihell.dice.blog.model.blog;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bihell.dice.blog.enums.PostStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 文章 Model
 *
 * @author bihell
 * @since 2017/7/8 9:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Article extends Model<Article> {

    @TableId
    private Integer id;
    @Size(max = 255,message = "文章标题字数不能超过255")
    @NotNull(message = "文章标题不能为空")
    private String title;
    @Size(max = 200000,message = "文章内容字数不能超过200000")
    @NotNull(message = "文章内容不能为空")
    private String content;
    private Integer hits;
    private String tags;
    private String category;
    private Integer priority;
    private PostStatusEnum status;
    private String type;
    private Boolean allowComment;
    private Integer commentCount;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 最后修改人
     */
    private Integer modifier;

    /**
     * 逻辑删除标识
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted = 0;
}
