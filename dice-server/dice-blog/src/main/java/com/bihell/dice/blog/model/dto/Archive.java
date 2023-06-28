package com.bihell.dice.blog.model.dto;

import com.bihell.dice.blog.model.blog.Article;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 归档 Dto
 *
 * @author bihell
 * @since 2017/9/21 11:24
 */
@Data
@Accessors(chain = true)
public class Archive {

    private String dateStr;

    private LocalDateTime date;

    private Integer count;

    private List<Article> articles;
}
