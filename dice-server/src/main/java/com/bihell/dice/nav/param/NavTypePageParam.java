package com.bihell.dice.nav.param;

import com.bihell.dice.commons.pagination.BasePageOrderParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  分页参数对象
 * </pre>
 *
 * @author tpxcer
 * @date 2021-06-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "分页参数")
public class NavTypePageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer status;
}
