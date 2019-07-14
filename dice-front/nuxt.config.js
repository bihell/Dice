import sitemapConfig from './config/sitemap-config'
import feedConfig from './config/feed-config'
import defaultConfig from './config/default-config'

// noinspection JSAnnotator
export default {
  /*
   ** Headers of the page
   */
  head: {
    title: defaultConfig.meta_title,
    titleTemplate: '%s - BIHell',
    meta: [
      { charset: 'utf-8' },
      { 'http-equiv': 'cleartype', content: 'on' },
      { 'http-equiv': 'Cache-Control' },
      {
        name: 'viewport',
        content: 'width=device-width, initial-scale=1, user-scalable=no'
      },
      {
        hid: 'description',
        name: 'description',
        content: defaultConfig.meta_description
      },
      {
        hid: 'keywords',
        name: 'keywords',
        content: defaultConfig.meta_keywords
      },
      { hid: 'author', name: 'author', content: defaultConfig.meta_author },
      {
        hid: 'google-site-verification',
        name: 'google-site-verification',
        content: defaultConfig.google_site_verification
      },
      {
        hid: 'baidu-site-verification',
        name: 'baidu-site-verification',
        content: defaultConfig.baidu_site_verification
      }
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/favicon.ico' },
      {
        rel: 'stylesheet',
        type: 'text/css',
        href: 'https://fonts.font.im/css?family=Roboto|Source+Code+Pro'
      }
    ],
    noscript: [{ innerHTML: 'This website requires JavaScript.' }]
  },
  /*
   ** Global CSS
   */
  css: [
    { src: '~assets/css/main.css' },
    { src: '~assets/css/normalize.css' },
    { src: '~assets/css/style.css' },
    'highlight.js/styles/tomorrow.css',
    { src: '~assets/css/icon.css' }
  ],
  modules: ['@nuxtjs/sitemap', '@nuxtjs/feed'],
  sitemap: sitemapConfig.config,
  feed: feedConfig.config,
  plugins: [
    { src: '~plugins/highlight.js', mode: 'client' },
    { src: '~plugins/clickoutside.js', mode: 'client' },
    { src: '~plugins/ga.js', mode: 'client' },
    { src: '~plugins/filters.js' },
    { src: '~plugins/global.js' },
    { src: '~plugins/tools.js' }
  ],
  router: {
    linkActiveClass: 'active',
    // nuxt 的bug,scrollToTop不生效，要重写scrollBehavior方法
    scrollBehavior: function(to, from, savedPosition) {
      if (savedPosition) {
        return savedPosition
      } else {
        let position = {}
        if (to.matched.length < 2) {
          position = { x: 0, y: 0 }
        } else if (
          to.matched.some(r => r.components.default.options.scrollToTop)
        ) {
          position = { x: 0, y: 0 }
        }
        if (to.hash) {
          position = { selector: to.hash }
        }
        return position
      }
    }
  },
  /*
   ** Customize the progress bar color
   */
  loading: {
    color: '#5764c6'
  },
  /*
   ** Build configuration
   */
  build: {
    extractCSS: true,
    /*
     ** Run ESLint on save
     */
    extend(config, { isDev, isClient }) {
      if (isDev && isClient) {
        config.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /(node_modules)/
        })
      }
    }
  }
}
