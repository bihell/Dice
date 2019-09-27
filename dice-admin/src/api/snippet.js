import request from '@/utils/request'
import util from '../utils/dice'
import Qs from 'qs'

// 根据 id 获取代码段
export function getSnippetById(snippetId) {
  const params = {
    snippetId: snippetId
  }
  return request(
    {
      url: '/admin/snippet',
      method: 'get',
      transformRequest: [function(params) {
        return Qs.stringify(params)
      }],
      params
    }
  )
}

// 获取标签列表
export function getAllTags(querySnippet) {
  const params = {
    type: util.STATIC.META_SNIPPET_TAG,
    title: querySnippet.title,
    snippetFileContent: querySnippet.snippetFileContent
  }
  return request({
    url: '/admin/meta',
    method: 'get',
    transformRequest: [function(params) {
      return Qs.stringify(params)
    }],
    params
  })
}

// 根据标签 id 获取代码段标题
export function getSnippetByMeta(metaId) {
  const params = {
    metaId: metaId
  }
  return request({
    url: '/admin/snippet/snippet_title',
    method: 'get',
    transformRequest: [function(params) {
      return Qs.stringify(params)
    }],
    params
  })
}

// 保存或更新代码段
export function saveSnippet(data) {
  return request({
    url: '/admin/snippet',
    method: 'post',
    data
  })
}

// 删除代码段
export function deleteSnippet(snippetId) {
  const params = {
    snippetId: snippetId
  }
  return request({
    url: '/admin/snippet',
    method: 'delete',
    transformRequest: [function(params) {
      return Qs.stringify(params)
    }],
    params
  })
}

