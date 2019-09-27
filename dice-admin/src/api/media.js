import request from '@/utils/request'
import Qs from 'qs'

export function pageMedia(pageSize, pageNum) {
  const params = {
    pageSize: pageSize,
    pageNum: pageNum
  }
  return request(
    {
      url: '/admin/media',
      method: 'get',
      transformRequest: [function(params) {
        return Qs.stringify(params)
      }],
      params
    }
  )
}

export function deleteMedia(id) {
  return request(
    {
      url: '/admin/media/' + id,
      method: 'delete'
    }
  )
}

