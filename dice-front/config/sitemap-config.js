import api from '../plugins/api'
import tools from '../plugins/tools'

// 静态页面,通过routes设置其url
const STATIC_ROUTE = ['/', '/about', '/archive', '/category']

const config = {
  routes() {
    return api.getOptions().then(optionsRes => {
      let url = ''
      if (
        optionsRes.success &&
        optionsRes.data &&
        optionsRes.data.blog_website
      ) {
        url = tools.formatWebsite(optionsRes.data.blog_website)
      }
      return api.getArticles(1, 999).then(articleRes => {
        const routes = articleRes.data.list.map(
          article => url + '/article/' + article.id
        )
        STATIC_ROUTE.forEach(route => {
          routes.push(url + route)
        })
        return routes
      })
    })
  },
  exclude: STATIC_ROUTE
}

export default {
  config
}
