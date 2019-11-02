import request from '@/utils/request'
import util from '@/utils/dice.js'

// 保存文章
export function saveArticle(data) {
  return request({
    url: '/admin/article',
    method: 'post',
    data
  })
}

// 获取文章列表
export function getArticles(params) {
  return request({
    url: '/admin/article',
    method: 'get',
    params: params
  })
}

// 获取评论列表
export function getComments(params) {
  return request({
    url: '/admin/comment',
    method: 'get',
    params: params
  })
}

// 获取自定义页面列表
export function getPages(params) {
  return request({
    url: '/admin/page',
    method: 'get',
    params: params
  })
}

export function getOptions() {
  return request({
    url: '/admin/option/all',
    method: 'get'
  })
}

export function saveOptions(options) {
  return request({
    url: '/admin/option/save',
    method: 'post',
    params: options
  })
}

export function getArticle(id) {
  return request({
    url: '/admin/article/' + id,
    method: 'get'
  })
}

export function deleteArticle(id) {
  return request({
    url: '/admin/article/' + id,
    method: 'delete'
  })
}

export function getCommentDetail(id) {
  return request({
    url: '/admin/comment/' + id,
    method: 'get'
  })
}

export function deleteComment(id) {
  return request({
    url: '/admin/comment/' + id,
    method: 'delete'
  })
}

export function getAllCategories() {
  const params = {
    type: util.STATIC.META_CATEGORY
  }
  return request({
    url: '/admin/meta',
    method: 'get',
    params: params
  })
}

export function getAllTags() {
  const params = {
    type: util.STATIC.META_TAG
  }
  return request({
    url: '/admin/meta',
    method: 'get',
    params: params
  })
}

export function saveCategory(name) {
  const params = {
    name: name,
    type: util.STATIC.META_CATEGORY
  }
  return request({
    url: '/admin/meta',
    method: 'post',
    params: params
  })
}

export function saveTag(name) {
  const params = {
    name: name,
    type: util.STATIC.META_TAG
  }
  return request({
    url: '/admin/meta',
    method: 'post',
    params: params
  })
}

export function updateCategory(id, name) {
  const params = {
    name: name,
    type: util.STATIC.META_CATEGORY
  }
  return request({
    url: '/admin/meta' + id,
    method: 'post',
    params: params
  })
}

export function updateTag(id, name) {
  const params = {
    name: name,
    type: util.STATIC.META_TAG
  }
  return request({
    url: '/admin/meta' + id,
    method: 'post',
    params: params
  })
}

export function deleteCategory(name) {
  const params = {
    name: name,
    type: util.STATIC.META_CATEGORY
  }
  return request({
    url: '/admin/meta',
    method: 'delete',
    params: params
  })
}

export function deleteTag(name) {
  const params = {
    name: name,
    type: util.STATIC.META_TAG
  }
  return request({
    url: '/admin/meta',
    method: 'delete',
    params: params
  })
}

export function getPage(id) {
  return request({
    url: '/admin/page/' + id,
    method: 'get'
  })
}

export function savePage(page) {
  return request({
    url: '/admin/page',
    method: 'post',
    data: page
  })
}

export function deletePage(id) {
  return request({
    url: '/admin/page/' + id,
    method: 'delete'
  })
}
