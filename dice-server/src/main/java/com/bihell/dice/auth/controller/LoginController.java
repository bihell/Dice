package com.bihell.dice.auth.controller;

import com.bihell.dice.auth.service.LoginService;
import com.bihell.dice.commons.api.ApiResult;
import com.bihell.dice.commons.model.LoginModel;
import com.bihell.dice.commons.vo.RoleInfoVO;
import com.bihell.dice.commons.utils.SecurityUtil;
import com.bihell.dice.system.service.SysUserService;
import com.bihell.dice.system.vo.RouteItemVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 登录控制器
 *
 * @author haseochen
 */
@Slf4j
@RestController
@Tag(name = "系统登录API")
@RequestMapping("/v1/api/admin/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginController {
    private final LoginService loginService;
    private final SysUserService sysUserService;

    /**
     * 登陆
     *
     * @param loginModel 登陆模型
     * @return 令牌
     */
    @PostMapping("/login")
    public ApiResult login(@RequestBody LoginModel loginModel) {
        var token = loginService.login(loginModel);
        return ApiResult.success(Map.of("token", token));
    }

    @GetMapping("/getMenuList")
    public ApiResult<List<RouteItemVO>> getMenuList() throws Exception {
        return ApiResult.success(sysUserService.getMenuList());
    }

    @GetMapping("/getPermCode")
    public ApiResult<Set<String>> getPermCode() throws Exception {
        return ApiResult.success(sysUserService.getPermCode());
    }

    /**
     * 根据token获取系统登录用户信息
     *
     * @return
     */
    @GetMapping("/getSysUserInfo")
    @Operation(summary = "根据token获取系统登录用户信息")
    public ApiResult<Map<String, Object>> getSysUser() throws Exception {
        List<RoleInfoVO> roles = SecurityUtil.getRoles();
        return ApiResult.okMap("roles", roles);
    }
}
