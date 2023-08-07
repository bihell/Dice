package com.bihell.dice.foobar.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 修改FooBar参数
 *
 * @author tpxcer
 * @since 2023-07-31
 */
@Data
@Schema(description = "修改FooBar参数")
public class FooBarUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @NotNull(message = "主键不能为空")
    private Long id;

    @Schema(description = "名称")
    @NotBlank(message = "名称不能为空")
    @Length(max = 20, message = "名称长度超过限制")
    private String name;

    @Schema(description = "Foo")
    @Length(max = 100, message = "Foo长度超过限制")
    private String foo;

    @Schema(description = "Bar")
    @Length(max = 100, message = "Bar长度超过限制")
    private String bar;

    @Schema(description = "状态，0：禁用，1：启用")
    private Boolean status;

}


