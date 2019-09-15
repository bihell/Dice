import { get, post } from './http'

const api = {
  getArticles(page, limit) {
    const params = {
      page: page,
      limit: limit || 5
    }
    return get('/article', params)
  },
  getArticle(id) {
    return get('/article/' + id)
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
      articleId: articleId,
      page: page,
      limit: limit || 5
    }
    return get('comment', params)
  },
  postComment(articleId, pId, content, name, email, website) {
    const params = {
      articleId: articleId,
      pId: pId,
      content: content,
      name: name,
      email: email,
      website: website
    }
    return post('/comment', params)
  },
  assessComment(commentId, assess) {
    const params = {
      assess: assess
    }
    return post('/comment/' + commentId + '/assess', params)
  },
  getOptions() {
    return get('/option')
  }
}

export default api
