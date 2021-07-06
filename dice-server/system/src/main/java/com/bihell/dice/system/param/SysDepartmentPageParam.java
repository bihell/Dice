package com.bihell.dice.system.param;

import com.bihell.dice.framework.core.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * 部门 查询参数对象
 * </pre>
 *
 * @author geekidea
 * @date 2019-10-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysDepartmentPageParam对象", description = "部门查询参数")
public class SysDepartmentPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;



}
