package com.bihell.dice.foobar.req;

import com.bihell.dice.commons.pagination.BasePageOrderParam;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * FooBar查询参数
 *
 * @author tpxcer
 * @since 2023-07-31
 */
@Data
@Schema(description = "FooBar查询参数")
public class FooBarRequest extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;

}

