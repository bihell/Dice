import { get, post } from './http'

const api = {
  getArticles(page, limit) {
    const params = {
      page,
      limit: limit || 5
    }
    return get('/article', params)
  },
  getArticle(id, token) {
    const params = {
      token
    }
    return get('/article/' + id, params)
  },
  getCategories() {
    return get('/category')
  },
  getTags() {
    return get('/tag')
  },
  getPageMenu() {
    return get('/page')
  },
  getPage(id) {
    return get('/page/' + id)
  },
  getArchives() {
    return get('/archive')
  },
  getComment(articleId, page, limit) {
    const params = {
      articleId,
      page,
      limit: limit || 5
    }
    return get('comment', params)
  },
  postComment(articleId, pId, content, name, email, website) {
    const params = {
      articleId,
      pId,
      content,
      name,
      email,
      website
    }
    return post('/comment', params)
  },
  assessComment(commentId, assess) {
    const params = {
      assess
    }
    return post('/comment/' + commentId + '/assess', params)
  },
  getOptions() {
    return get('/option')
  }
}

export default api
