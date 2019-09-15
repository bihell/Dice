import request from '@/utils/request2'
import Qs from 'qs'

export function pageMedia(limit, page) {
  const params = {
    limit: limit,
    page: page
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

