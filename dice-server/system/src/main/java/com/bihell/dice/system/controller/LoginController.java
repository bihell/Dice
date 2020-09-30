package com.bihell.dice.system.controller;

import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.log.annotation.OperationLogIgnore;
import com.bihell.dice.framework.shiro.util.JwtTokenUtil;
import com.bihell.dice.system.param.LoginParam;
import com.bihell.dice.system.service.LoginService;
import com.bihell.dice.system.vo.LoginSysUserTokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bihell.dice.framework.log.annotation.Module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器
 **/
@Slf4j
@RestController
@Module("system")
@Api(value = "系统登录API", tags = {"系统登录"})
@RequestMapping("/v1/api/admin/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginController {
    private final LoginService loginService;

    @PostMapping("login")
    @OperationLogIgnore
    @ApiOperation(value = "登录", notes = "系统用户登录", response = LoginSysUserTokenVo.class)
    public ApiResult<LoginSysUserTokenVo> login(@Validated @RequestBody LoginParam loginParam, HttpServletResponse response) throws Exception {
        LoginSysUserTokenVo loginSysUserTokenVo = loginService.login(loginParam);
        // 设置token响应头
        response.setHeader(JwtTokenUtil.getTokenName(), loginSysUserTokenVo.getToken());
        return ApiResult.ok(loginSysUserTokenVo, "登录成功");
    }

    @PostMapping("logout")
    @OperationLogIgnore
    @ApiOperation(value = "登出", notes = "系统用户登出")
    public ApiResult<String> logout(HttpServletRequest request) throws Exception {
        loginService.logout(request);
        return ApiResult.ok("退出成功");
    }
}
