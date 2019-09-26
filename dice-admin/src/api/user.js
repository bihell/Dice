import { post, get } from '@/utils/request'
import request from '@/utils/request2'

export function login(data) {
  return request({
    url: '/admin/user/login',
    method: 'post',
    data
  })
}

export function logout() {
  return post('/admin/user/logout')
}

const user = {
  getUser() {
    return get('/admin/user/user_info')
  },
  resetUser(username, email) {
    const params = {
      username: username,
      email: email
    }
    return post('/admin/user/reset/user', params)
  },
  resetPassword(oldPassword, newPassword) {
    const params = {
      oldPassword: oldPassword,
      newPassword: newPassword
    }
    return post('/admin/user/reset/password', params)
  }
}

export default {
  user
}
