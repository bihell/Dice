package com.bihell.dice.system.controller;

import com.alibaba.fastjson.JSON;
import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.log.annotation.OperationLogIgnore;
import com.bihell.dice.framework.shiro.util.JwtTokenUtil;
import com.bihell.dice.framework.shiro.vo.LoginSysUserVo;
import com.bihell.dice.system.param.LoginParam;
import com.bihell.dice.system.service.LoginService;
import com.bihell.dice.system.service.UserService;
import com.bihell.dice.system.vo.LoginSysUserTokenVo;
import com.bihell.dice.system.vo.RouteItemVO;
import com.bihell.dice.system.vo.SysUserQueryVo;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.bihell.dice.framework.log.annotation.Module;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 登录控制器
 *
 * @author haseochen*/
@Slf4j
@RestController
@Module("system")
@Api(value = "系统登录API", tags = {"系统登录"})
@RequestMapping("/v1/api/admin/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("login")
    @OperationLogIgnore
    @ApiOperation(value = "登录", notes = "系统用户登录", response = LoginSysUserTokenVo.class)
    public ApiResult<LoginSysUserTokenVo> login(@Validated @RequestBody LoginParam loginParam, HttpServletResponse response) throws Exception {
        LoginSysUserTokenVo loginSysUserTokenVo = loginService.login(loginParam);
        // 设置token响应头
        response.setHeader(JwtTokenUtil.getTokenName(), loginSysUserTokenVo.getToken());
        return ApiResult.ok(loginSysUserTokenVo, "登录成功");
    }

    @GetMapping("logout")
    @OperationLogIgnore
    @ApiOperation(value = "登出", notes = "系统用户登出")
    public ApiResult<String> logout(HttpServletRequest request) throws Exception {
        loginService.logout(request);
        return ApiResult.ok("退出成功");
    }

    @GetMapping("/getMenuList")
    public ApiResult<List<RouteItemVO>> getMenuList() throws Exception {
        return ApiResult.ok(userService.getMenuList());
    }

    @GetMapping("/getPermCode")
    public ApiResult<List<String>> getPermCode() throws Exception {
        return ApiResult.ok(userService.getPermCode());
    }

    /**
     * 根据token获取系统登录用户信息
     *
     * @return
     */
    @GetMapping("/getSysUserInfo")
    @ApiOperation(value = "根据token获取系统登录用户信息", response = SysUserQueryVo.class)
    public ApiResult<LoginSysUserVo> getSysUser() throws Exception {
        String token =  JwtTokenUtil.getToken();
        String tokenSha256 = DigestUtils.sha256Hex(token);
        LoginSysUserVo loginSysUserVo = (LoginSysUserVo) redisTemplate.opsForValue().get(tokenSha256);
        return ApiResult.ok(loginSysUserVo);

//        String json = "{\n" +
//                "    roles: ['admin'],\n" +
//                "    introduction: 'I am a super administrator',\n" +
//                "    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',\n" +
//                "    name: 'Super Admin'\n" +
//                "  }";
//        JSON array = JSON.parseObject(json);
//
//        return ApiResult.ok(array);
    }
}
