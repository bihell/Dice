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

// 菜单 group
export function getGroupList(params) {
  return request({
    url: '/admin/auth/group/list',
    method: 'get',
    params: params
  })
}
