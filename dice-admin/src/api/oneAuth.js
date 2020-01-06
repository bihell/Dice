import request from '@/utils/request'

export function getUserInfo(data) {
  return request({
    url: '/one_auth/get_user_info',
    method: 'post',
    data
  })
}

export function getMenuList(data) {
  return request({
    url: '/one_auth/get_group_info?project=dice',
    method: 'post',
    data
  })
}

