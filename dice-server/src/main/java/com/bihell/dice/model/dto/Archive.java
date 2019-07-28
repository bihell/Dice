package com.bihell.dice.model.dto;

import com.bihell.dice.model.domain.Article;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
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

    private Date date;

    private Integer count;

    private List<Article> articles;
}
