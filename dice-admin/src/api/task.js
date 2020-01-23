import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/admin/task/list',
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    url: '/admin/task',
    method: 'post',
    params
  })
}

export function remove(id) {
  return request({
    url: '/admin/task',
    method: 'delete',
    params: {
      id: id
    }
  })
}

export function logList(params) {
  return request({
    url: '/admin/task/logList',
    method: 'get',
    params
  })
}

export function enable(id) {
  return request({
    url: '/admin/task/enable/',
    method: 'POST',
    params: {
      taskId: id
    }
  })
}

export function disable(id) {
  return request({
    url: '/admin/task/disable/',
    method: 'POST',
    params: {
      taskId: id
    }
  })
}
