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

export function getAllSubSystem() {
  return request({
    url: '/admin/auth/project_list',
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
