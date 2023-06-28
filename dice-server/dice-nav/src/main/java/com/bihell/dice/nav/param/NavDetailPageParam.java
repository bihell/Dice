package com.bihell.dice.nav.param;

import com.bihell.dice.framework.core.pagination.BasePageOrderParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 导航明细表 分页参数对象
 * </pre>
 *
 * @author tpxcer
 * @date 2021-06-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "导航明细表分页参数")
public class NavDetailPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
    private Integer typeId;
    private String name;
    private String description;
    private String url;
}
