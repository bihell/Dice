package com.bihell.dice.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.bihell.dice.model.domain.Meta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 属性 Dto
 *
 * @author bihell
 * @since 2017/8/30 15:15
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class MetaDto extends Meta {

    private Integer count;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ArticleInfoDto> articles;
}
