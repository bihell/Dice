import Vue from 'vue'
import VueStorage from 'vue-ls'
import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './styles/element-variables.scss'

import '@/styles/index.scss' // global css
import App from './App'
import store from './store'
import router from './router'
import './icons' // icon
import './permission' // permission control
import './utils/error-log' // error log
import * as filters from './filters' // global filters
import VueHighlightJS from 'vue-highlightjs'
import prototype from './utils/prototype'

// Tell Vue.js to use vue-highlightjs
Vue.use(VueHighlightJS)
Vue.use(VueStorage, {
  namespace: 'dice_',
  name: 'ls',
  storage: 'local'
})
Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})
Vue.use(prototype)
// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
