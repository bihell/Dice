package com.bihell.dice.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author haseochen
 */
@Data
@Accessors(chain = true)
@Schema(description = "前端标签和分类Select值")
public class MetaQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Select名")
    private String label;

    @Schema(description = "Select值")
    private Integer value;
}
