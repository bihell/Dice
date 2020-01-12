import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/auth/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/admin/auth/logout',
    method: 'post'
  })
}

export function getUser() {
  return request({
    url: '/admin/auth/user_info',
    method: 'get'
  })
}

// 获取用户列表
export function getUserList(params) {
  return request({
    url: '/admin/auth/user/list',
    method: 'get',
    params: params
  })
}

// 获取用户列表
export function getAllUsers(params) {
  return request({
    url: '/admin/auth/user/list/all',
    method: 'get',
    params: params
  })
}

// 获取单个用户
export function getUserSingle(id) {
  return request({
    url: '/admin/auth/user/get',
    method: 'get',
    params: { id }
  })
}

// 获取单个用户
export function updateUserSingle(data) {
  return request({
    url: '/admin/auth/user/update',
    method: 'post',
    data
  })
}

// 增加用户
export function addUser(data) {
  return request({
    url: '/admin/auth/user/add',
    method: 'post',
    data
  })
}

// 保存用户所属角色
export function saveRoles(data) {
  return request({
    url: '/admin/auth/user/assign/role',
    method: 'post',
    data
  })
}

// 项目
export function getAllSubSystem() {
  return request({
    url: '/admin/auth/project/project_list',
    method: 'get'
  })
}

// 菜单 item
export function getItemList(params) {
  return request({
    url: '/admin/auth/item/list',
    method: 'get',
    params: params
  })
}

/**
 *一级菜单 group
 */

// 获取一级菜单
export function getGroupList(params) {
  return request({
    url: '/admin/auth/group/list',
    method: 'get',
    params: params
  })
}

// 新增一个一级菜单
export function addGroup(data) {
  return request({
    url: '/admin/auth/group/add',
    method: 'post',
    data
  })
}

// 获取一个一级菜单
export function getGroupSingle(id) {
  return request({
    url: '/admin/auth/group/get',
    method: 'get',
    params: { id }
  })
}

// 更新一个一级菜单
export function updateGroupSingle(data) {
  return request({
    url: '/admin/auth/group/update',
    method: 'post',
    data
  })
}

/**
 * 二级菜单 Class
 */

// 新增一个二级菜单
export function addclasses(data) {
  return request({
    url: '/admin/auth/classes/add',
    method: 'post',
    data
  })
}

// 获取一个二级菜单
export function getClasses(id) {
  return request({
    url: '/admin/auth/classes/get',
    method: 'get',
    params: { id }
  })
}

// 更新一个二级菜单
export function updateClassesSingle(data) {
  return request({
    url: '/admin/auth/classes/update',
    method: 'post',
    data
  })
}

/**
 * 三级功能 item
 */

// 新增一个itemCode
export function addItem(data) {
  return request({
    url: '/admin/auth/item/add',
    method: 'post',
    data
  })
}

// 获取一个itemCode
export function getItem(id) {
  return request({
    url: '/admin/auth/item/get',
    method: 'get',
    params: { id }
  })
}

// 更新一个itemcode
export function updateItem(data) {
  return request({
    url: '/admin/auth/item/update',
    method: 'post',
    data
  })
}

export function saveItemApi(data) {
  return request({
    url: '/admin/auth/item/assign/api',
    method: 'post',
    data
  })
}

/**
 * api
 */

// 获取api列表
export function getApiList(params) {
  return request({
    url: '/admin/auth/api/list',
    method: 'get',
    params: params
  })
}

// 新增api
export function addApi(data) {
  return request({
    url: '/admin/auth/api/add',
    method: 'post',
    data
  })
}

// 获取一个api
export function getApi(id) {
  return request({
    url: '/admin/auth/api/get',
    method: 'get',
    params: { id }
  })
}

// 更新一个api
export function updateApi(data) {
  return request({
    url: '/admin/auth/api/update',
    method: 'post',
    data
  })
}

/**
 * role
 */

// 保存角色API
export function saveRoleApi(data) {
  return request({
    url: '/admin/auth/role/assign/api',
    method: 'post',
    data
  })
}

// 保存角色内容
export function saveRoleContent(data) {
  return request({
    url: '/admin/auth/role/assign/content',
    method: 'post',
    data
  })
}

// 保存角色用户
export function saveRoleUser(data) {
  return request({
    url: '/admin/auth/role/assign/User',
    method: 'post',
    data
  })
}

// 保存角色操作项
export function saveRoleItem(data) {
  return request({
    url: '/admin/auth/role/assign/item',
    method: 'post',
    data
  })
}

// 新增role
export function addRole(data) {
  return request({
    url: '/admin/auth/role/add',
    method: 'post',
    data
  })
}

// 获取角色列表
export function getRoleList(params) {
  return request({
    url: '/admin/auth/role/list',
    method: 'get',
    params: params
  })
}

// 获取一个角色
export function getRoleSingle(id) {
  return request({
    url: '/admin/auth/role/get',
    method: 'get',
    params: { id }
  })
}

// 更新一个二级菜单
export function updateRoleSingle(data) {
  return request({
    url: '/admin/auth/role/update',
    method: 'post',
    data
  })
}

/**
 * content
 */

// 获取内容列表
export function getContentList(params) {
  return request({
    url: '/admin/auth/content/list',
    method: 'get',
    params: params
  })
}

// 新增内容
export function addContent(data) {
  return request({
    url: '/admin/auth/content/add',
    method: 'post',
    data
  })
}

// 获取一个内容
export function getContentSingle(id) {
  return request({
    url: '/admin/auth/content/get',
    method: 'get',
    params: { id }
  })
}

// 更新一个内容
export function updateContentSingle(data) {
  return request({
    url: '/admin/auth/content/update',
    method: 'post',
    data
  })
}
