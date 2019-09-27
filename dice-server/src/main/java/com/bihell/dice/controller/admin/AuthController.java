package com.bihell.dice.controller.admin;

import com.bihell.dice.controller.BaseController;
import com.bihell.dice.model.domain.User;
import com.bihell.dice.model.params.LoginParam;
import com.bihell.dice.service.UserService;
import com.bihell.dice.utils.RestResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 后台用户验证 Controller
 *
 * @author bihell
 * @since 2017/7/11 20:15
 */
@RestController
@RequestMapping("/v1/api/admin/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController extends BaseController {

    private final UserService userService;

    /**
     * 后台登录
     *
     * @param loginParam Login param
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("login")
    @ApiOperation("Login")
    public RestResponse auth(@RequestBody @Valid LoginParam loginParam) {
        return RestResponse.ok(userService.authenticate(loginParam));
    }

    /**
     * 登出
     *
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("logout")
    @ApiOperation("Logs out (Clear session)")
    public RestResponse logout() {
        userService.clearToken();
        return RestResponse.ok();
    }

    /**
     * 修改用户名密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return {@see Boolean}
     */
    @PostMapping("reset/password")
    public RestResponse resetPassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        User user = this.user();
        if (null == user) {
            return RestResponse.fail("没有用户登陆");
        }

        if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(oldPassword)) {
            return RestResponse.fail("填写数据不能为空");
        }

        boolean result = userService.resetPassword(user.getUsername(), oldPassword, newPassword);

        this.logout();
        return RestResponse.ok(result);
    }

    /**
     * 修改用户信息
     *
     * @return {@see Boolean}
     */
    @PostMapping("reset/user")
    public RestResponse resetUser(@RequestParam String username, @RequestParam String email) {
        User user = this.user();
        if (null == user) {
            return RestResponse.fail("没有用户登陆");
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(email)) {
            return RestResponse.fail("填写数据不能为空");
        }

        boolean result = userService.resetUser(user.getUsername(), username, email);
        this.logout();
        return RestResponse.ok(result);
    }

    /**
     * 获取用户名
     *
     * @return {@see String}
     */
    @GetMapping("user_info")
    public RestResponse getUser() {
        User user = this.user();
        return RestResponse.ok(user);
    }

}
