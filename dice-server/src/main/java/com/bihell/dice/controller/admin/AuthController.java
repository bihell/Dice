package com.bihell.dice.controller.admin;

import com.bihell.dice.controller.BaseController;
import com.bihell.dice.model.domain.User;
import com.bihell.dice.service.UserService;
import com.bihell.dice.util.DiceConsts;
import com.bihell.dice.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 后台用户验证 Controller
 *
 * @author bihell
 * @since 2017/7/11 20:15
 */
@RestController
@RequestMapping("/v1/api/admin/user")
public class AuthController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 后台登录
     *
     * @param response   {@link HttpServletResponse}
     * @param username   用户名
     * @param password   密码
     * @param rememberMe 是否记住
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("login")
    public RestResponse login(HttpServletResponse response, @RequestParam String username, @RequestParam String password, String rememberMe) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return RestResponse.fail("用户名和密码不能为空");
        }

        User user = userService.login(username, password);


        request.getSession().setAttribute(DiceConsts.USER_SESSION_KEY, user);
        System.out.println(request.getSession());

        return RestResponse.ok(user);
    }

    /**
     * 登出
     *
     * @return {@see RestResponse.ok()}
     */
    @PostMapping("logout")
    public RestResponse logout() {
        User user = this.user();
        System.out.println(request.getSession());
        if (null == user) {
            return RestResponse.fail("没有用户登陆");
        }

        request.getSession().removeAttribute(DiceConsts.USER_SESSION_KEY);
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
        if (null == user) {
            return RestResponse.fail("没有用户登陆");
        }

        return RestResponse.ok(user);
    }

}
