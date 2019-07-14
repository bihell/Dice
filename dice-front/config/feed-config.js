import api from '../plugins/api'
import tools from '../plugins/tools'
import defaultConfig from './default-config'

const config = [
  // A default feed configuration object
  {
    path: '/feed.xml', // The route to your feed.
    async create(feed) {
      const optionsResp = await api.getOptions()
      const options = optionsResp.data
      feed.options = {
        title: options.meta_title || defaultConfig.meta_title,
        link: tools.formatWebsite(options.blog_website) + '/feed.xml',
        description: options.meta_description || defaultConfig.meta_description
      }

      const articleResp = await api.getArticles(1, 999)
      const articles = articleResp.data.list
      articles.forEach(article => {
        feed.addItem({
          title: article.title,
          id: article.id,
          link:
            tools.formatWebsite(options.blog_website) +
            '/article/' +
            article.id,
          description: article.content,
          content: article.content
        })
      })
      feed.addCategory('Nuxt.js')
    }, // The create function (see below)
    cacheTime: 1000 * 60 * 15, // How long should the feed be cached
    type: 'rss2' // Can be: rss2, atom1, json1
  }
]

export default {
  config
}
