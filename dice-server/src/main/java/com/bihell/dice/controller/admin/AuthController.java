package com.bihell.dice.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.controller.BaseController;
import com.bihell.dice.mapper.AuthApiMapper;
import com.bihell.dice.mapper.AuthItemMapper;
import com.bihell.dice.model.domain.*;
import com.bihell.dice.model.dto.Pagination;
import com.bihell.dice.model.params.LoginParam;
import com.bihell.dice.model.params.QueryParam;
import com.bihell.dice.service.AuthApiService;
import com.bihell.dice.service.AuthGroupService;
import com.bihell.dice.service.AuthItemService;
import com.bihell.dice.service.UserService;
import com.bihell.dice.utils.DiceConsts;
import com.bihell.dice.utils.RestResponse;
import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
@RequestMapping("/v1/api/admin/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController extends BaseController {

    private final UserService userService;
    private final AuthItemMapper authItemMapper;
    private final AuthGroupService authGroupService;
    private final AuthItemService authItemService;
    private final AuthApiMapper authApiMapper;
    private final AuthApiService authApiService;

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

    /**
     * 获取用户列表
     *
     * @param currentPage 当前页面
     * @param pageSize    每页数量
     */
    @GetMapping("/user_list")
    public RestResponse list(@RequestParam(required = false, defaultValue = "1") Integer currentPage,
                             @RequestParam(required = false, defaultValue = DiceConsts.PAGE_SIZE) Integer pageSize, User userQuery) {
        IPage<User> userList = userService.getUserList(currentPage, pageSize, userQuery);
        return RestResponse.ok(new Pagination<User>(userList));
    }

    /**
     * 获取项目列表
     **/
    @GetMapping("/project/project_list")
    public RestResponse getProjectList() {
        return RestResponse.ok(new DimProject().selectAll());
    }

    @GetMapping("/item/list")
    public RestResponse getItemList(QueryParam param) {
        return RestResponse.ok(authItemMapper.queryByParam(param));
    }

    @GetMapping("/group/list")
    public RestResponse getGroupList(QueryParam param) {
        return RestResponse.ok(authGroupService.getGroupList(param));
    }

    @PostMapping("/group/add")
    public RestResponse addGroup(@RequestBody AuthGroup authGroup) {
        if (authGroup.insert()) {
            return RestResponse.ok(authGroup);
        } else {
            return RestResponse.fail("插入失败");
        }
    }

    @GetMapping("/group/get")
    public RestResponse getGroupSingle(@RequestParam Integer id) {
        return RestResponse.ok(new AuthGroup().selectById(id));
    }

    @PostMapping("/group/update")
    public RestResponse updateGroupSingle(@RequestBody AuthGroup authGroup) {
        if (authGroup.updateById()) {
            return RestResponse.ok(authGroup);
        } else {
            return RestResponse.fail("更新失败");
        }
    }

    @PostMapping("/classes/add")
    public RestResponse addClass(@RequestBody AuthClasses authClasses) {
        if (authClasses.insert()) {
            return RestResponse.ok(authClasses);
        } else {
            return RestResponse.fail("插入失败");
        }
    }

    @GetMapping("/classes/get")
    public RestResponse getClassesSingle(@RequestParam Integer id) {
        return RestResponse.ok(new AuthClasses().selectById(id));
    }

    @PostMapping("/classes/update")
    public RestResponse updateClassesSingle(@RequestBody AuthClasses authClasses) {
        if (authClasses.updateById()) {
            return RestResponse.ok(authClasses);
        } else {
            return RestResponse.fail("更新失败");
        }
    }

    @PostMapping("/item/add")
    public RestResponse addItem(@RequestBody AuthItem authItem) {
        return RestResponse.ok(authItemService.save(authItem));
    }

    @GetMapping("/item/get")
    public RestResponse getItemSingle(@RequestParam Integer id) {
        return RestResponse.ok(new AuthItem().selectById(id));
    }

    @PostMapping("/item/update")
    public RestResponse updateItemSingle(@RequestBody AuthItem authItem) {
        return RestResponse.ok(authItemService.update(authItem));
    }

    @GetMapping("/api/list")
    public RestResponse getApiList(QueryParam queryParam) {
        return RestResponse.ok(new Pagination<AuthApi>(authApiMapper.queryByParam(new Page<>(queryParam.getPageNum(), queryParam.getPageSize()), queryParam)));
    }

    /**
     * 批量保存，根据换行符来分隔，apiPath是个数组
     */
    @PostMapping(value = "/api/add")
    public RestResponse addApi(@RequestBody AuthApi authApi) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(authApi.getApiPaths()), "参数缺失");

        for (String apiPath : authApi.getApiPaths()) {
            authApi.setApiPath(apiPath);
            authApiService.add(authApi);
        }

        return RestResponse.ok();
    }

    @GetMapping("/api/get")
    public RestResponse getApiSingle(@RequestParam Integer id) {
        return RestResponse.ok(new AuthApi().selectById(id));
    }

    @PostMapping("/api/update")
    public RestResponse updateApiSingle(@RequestBody AuthApi authApi) {
        System.out.println(authApi);
        return RestResponse.ok(authApiService.update(authApi));
    }
}
