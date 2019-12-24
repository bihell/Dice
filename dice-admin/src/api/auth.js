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

export function resetUser(username, email) {
  const params = {
    username: username,
    email: email
  }
  return request({
    url: '/admin/auth/reset/user',
    method: 'post',
    params: params
  })
}

export function resetPassword(oldPassword, newPassword) {
  const params = {
    oldPassword: oldPassword,
    newPassword: newPassword
  }
  return request({
    url: '/admin/auth/reset/password',
    method: 'post',
    params: params
  })
}

// 获取文章列表
export function getUserList(params) {
  return request({
    url: '/admin/auth/user_list',
    method: 'get',
    params: params
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
