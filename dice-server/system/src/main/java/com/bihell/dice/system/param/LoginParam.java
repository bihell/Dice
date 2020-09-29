package com.bihell.dice.system.param;

import com.bihell.dice.framework.shiro.service.LoginUsername;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录参数
 **/
@Data
@ApiModel("登录参数")
public class LoginParam implements LoginUsername {
	private static final long serialVersionUID = 2854217576695117356L;

	@NotBlank(message = "请输入账号")
    @ApiModelProperty(value = "账号", example = "admin")
    private String username;

    @NotBlank(message = "请输入密码")
    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    @ApiModelProperty("验证码Token")
    private String verifyToken;

    @ApiModelProperty("验证码")
    private String code;

}
