import { get, post, del } from '../utils/request'
import request from '@/utils/request2'
import util from '../utils/dice.js'
// import Qs from 'qs'

const blog = {
  getUser() {
    return get('/admin/user')
  },
  getOptions() {
    return get('/admin/option/all')
  },
  saveOptions(options) {
    return post('/admin/option/save', options)
  },
  getArticleCount() {
    return get('/admin/article/count')
  },
  getLogs(page) {
    const params = {
      page: page
    }
    return get('/admin/site/logs', params)
  },
  getArticle(id) {
    return get('/admin/article/' + id)
  },
  saveArticle(article) {
    return post('/admin/article', article)
  },
  deleteArticle(id) {
    return del('/admin/article/' + id)
  },
  getCommentDetail(id) {
    return get('/admin/comment/' + id)
  },
  deleteComment(id) {
    return del('/admin/comment/' + id)
  },
  getCommentCount() {
    return get('/admin/comment/count')
  },
  getAllCategories() {
    const params = {
      type: util.STATIC.META_CATEGORY
    }
    return get('/admin/meta', params)
  },
  getAllTags() {
    const params = {
      type: util.STATIC.META_TAG
    }
    return get('/admin/meta', params)
  },
  saveCategory(name) {
    const params = {
      name: name,
      type: util.STATIC.META_CATEGORY
    }
    return post('/admin/meta', params)
  },
  saveTag(name) {
    const params = {
      name: name,
      type: util.STATIC.META_TAG
    }
    return post('/admin/meta', params)
  },
  updateCategory(id, name) {
    const params = {
      name: name,
      type: util.STATIC.META_CATEGORY
    }
    return post('/admin/meta/' + id, params)
  },
  updateTag(id, name) {
    const params = {
      name: name,
      type: util.STATIC.META_TAG
    }
    return post('/admin/meta/' + id, params)
  },
  deleteCategory(name) {
    const params = {
      name: name,
      type: util.STATIC.META_CATEGORY
    }
    return del('/admin/meta', params)
  },
  deleteTag(name) {
    const params = {
      name: name,
      type: util.STATIC.META_TAG
    }
    return del('/admin/meta', params)
  },
  getPage(id) {
    return get('/admin/page/' + id)
  },
  savePage(page) {
    return post('/admin/page', page)
  },
  deletePage(id) {
    return del('/admin/page/' + id)
  }
}

export default {
  blog
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
