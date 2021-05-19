package com.bihell.dice.blog.param;

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
@ApiModel(value = "CommentParam对象", description = "文章评论参数")
public class CommentPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
