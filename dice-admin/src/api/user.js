import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/user/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/admin/user/logout',
    method: 'post'
  })
}

export function getUser() {
  return request({
    url: '/admin/user/user_info',
    method: 'get'
  })
}

export function resetUser(username, email) {
  const params = {
    username: username,
    email: email
  }
  return request({
    url: '/admin/user/reset/user',
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
    url: '/admin/user/reset/password',
    method: 'post',
    params: params
  })
}
