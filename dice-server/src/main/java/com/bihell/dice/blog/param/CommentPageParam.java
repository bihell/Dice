package com.bihell.dice.blog.param;

import com.bihell.dice.commons.pagination.BasePageOrderParam;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "文章评论参数")
public class CommentPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
