import Vue from 'vue'
import gravarar from 'gravatar'
import util from './tools'
import * as filters from './filters'
import clickOutside from './clickoutside'
import { highlight } from './highlight'

Vue.prototype.$util = util
Vue.prototype.$gravarar = gravarar

// 引用 fiters
const fs = filters
Object.keys(filters).forEach(key => Vue.filter(key, fs[key]))

// 引用clickOutside
Vue.directive('click-outside', clickOutside)
// 引用highlight
Vue.directive('highlight', highlight)
