package com.bihell.dice.system.vo;

import com.bihell.dice.framework.shiro.service.LoginToken;
import com.bihell.dice.framework.shiro.vo.LoginSysUserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel("登录用户信息TokenVO")
public class LoginSysUserTokenVo implements LoginToken {

    private static final long serialVersionUID = -2138450422989081056L;

    @ApiModelProperty("token")
    private String token;

    /**
     * 登录用户对象
     */
    private LoginSysUserVo loginSysUserVo;
}
