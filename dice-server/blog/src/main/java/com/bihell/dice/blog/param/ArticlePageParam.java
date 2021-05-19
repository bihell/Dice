package com.bihell.dice.blog.param;

import com.bihell.dice.blog.enums.PostStatusEnum;
import com.bihell.dice.framework.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * 文章查询参数对象
 * @author haseochen
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ArticlePageParam对象", description = "文章查询参数")
public class ArticlePageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
    private PostStatusEnum status;
    private Integer priority;
    private String title;
    private String content;
}
