package com.bihell.dice.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bihell.dice.controller.BaseController;
import com.bihell.dice.mapper.auth.*;
import com.bihell.dice.model.auth.*;
import com.bihell.dice.model.dto.Pagination;
import com.bihell.dice.model.params.LoginParam;
import com.bihell.dice.model.params.QueryParam;
import com.bihell.dice.service.auth.*;
import com.bihell.dice.utils.RestResponse;
import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
    private final AuthRoleMapper authRoleMapper;
    private final AuthContentMapper authContentMapper;
    private final AuthContentService authContentService;
    private final AuthRoleService authRoleService;
    private final UserMapper userMapper;

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
     */
    @GetMapping("/user/list")
    public RestResponse list(QueryParam queryParam) {

        IPage<User> userList = userService.getUserList(queryParam);
        return RestResponse.ok(new Pagination<User>(userList));
    }

    /**
     * 获取所有用户
     */
    @GetMapping("/user/list/all")
    public RestResponse getAllUsers(QueryParam queryParam) {
        return RestResponse.ok(new Pagination<User>(userMapper.queryByParam(new Page<>(queryParam.getPageNum(), queryParam.getPageSize()), queryParam)));
    }

    @PostMapping("/user/add")
    public RestResponse addUser(@RequestBody User user) {
        userService.addUser(user);
        return RestResponse.ok();
    }

    @PostMapping("/user/assign/role")
    public RestResponse assignRole(@RequestBody User user) {
        userService.assignRole(user);
        return RestResponse.ok();
    }

    @PostMapping("/user/update")
    public RestResponse updateUserSingle(@RequestBody User user) {
        userService.updateUser(user);
        return RestResponse.ok();
    }

    @GetMapping("/user/get")
    public RestResponse getUserSingle(@RequestParam Integer id) {
        return RestResponse.ok(userService.getUserSingle(id));
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

    @PostMapping("/item/assign/api")
    public RestResponse saveItemApi(@RequestBody AuthItem authItem) {
        Preconditions.checkArgument(authItem != null && authItem.getId() != null, "参数缺失");
        authItemService.assignApi(authItem);
        return RestResponse.ok();
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
        return RestResponse.ok(authApiService.update(authApi));
    }


    /**
     * 给角色分配操作项
     */
    @PostMapping("/role/assign/item")
    public RestResponse assignRoleItem(@RequestBody AuthRole authRole) {
        Preconditions.checkArgument(authRole != null && authRole.getId() != null, "参数缺失");
        authRoleService.assignItem(authRole);
        return RestResponse.ok();
    }

    /**
     * 给角色分配API
     */
    @PostMapping("/role/assign/api")
    public RestResponse assignRoleApi(@RequestBody AuthRole authRole) {
        Preconditions.checkArgument(authRole != null && authRole.getId() != null, "参数缺失");
        authRoleService.assignApi(authRole);
        return RestResponse.ok();
    }

    /**
     * 给角色分配内容项
     */
    @PostMapping("/role/assign/content")
    public RestResponse assignRoleContent(@RequestBody AuthRole authRole) {
        Preconditions.checkArgument(authRole != null && authRole.getId() != null, "参数缺失");
        authRoleService.assignContent(authRole);
        return RestResponse.ok();
    }

    /**
     * 给角色分配用户
     */
    @PostMapping("/role/assign/user")
    public RestResponse assignRoleUser(@RequestBody AuthRole authRole) {
        Preconditions.checkArgument(authRole != null && authRole.getId() != null, "参数缺失");
        authRoleService.assignUser(authRole);
        return RestResponse.ok();
    }


    @PostMapping("/role/update")
    public RestResponse updateRoleSingle(@RequestBody AuthRole authRole) {
        return RestResponse.ok(authRoleService.update(authRole));
    }

    @PostMapping("/role/add")
    public RestResponse addRole(@RequestBody AuthRole authRole) {
        return RestResponse.ok(authRoleService.save(authRole));
    }

    @GetMapping("/role/list")
    public RestResponse getRoleList(QueryParam queryParam) {
        return RestResponse.ok(new Pagination<AuthRole>(authRoleMapper.queryByParam(new Page<>(queryParam.getPageNum(), queryParam.getPageSize()), queryParam)));
    }

    @GetMapping("/role/get")
    public RestResponse getRoleSingle(@RequestParam Integer id) {
        return RestResponse.ok(new AuthRole().selectById(id));
    }

    @GetMapping("/content/list")
    public RestResponse getContentList(QueryParam queryParam) {
        return RestResponse.ok(new Pagination<AuthContent>(authContentMapper.queryByParam(new Page<>(queryParam.getPageNum(), queryParam.getPageSize()), queryParam)));
    }

    @PostMapping("/content/add")
    public RestResponse addContent(@RequestBody AuthContent authContent) {
        return RestResponse.ok(authContentService.save(authContent));
    }

    @GetMapping("/content/get")
    public RestResponse getContentSingle(@RequestParam Integer id) {
        return RestResponse.ok(new AuthContent().selectById(id));
    }

    @PostMapping("/content/update")
    public RestResponse updateContentSingle(@RequestBody AuthContent authContent) {
        return RestResponse.ok(authContentService.update(authContent));
    }
}
