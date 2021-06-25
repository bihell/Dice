package com.bihell.dice.nav.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.bihell.dice.framework.core.pagination.BasePageOrderParam;

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
@ApiModel(value = "分页参数")
public class NavTypePageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer status;
}
