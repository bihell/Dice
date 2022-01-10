import Vue from 'vue'
import gravarar from 'gravatar'
import util from '@/plugins/tools'
import * as filters from '@/plugins/filters'
import clickOutside from '@/plugins/clickoutside'
import { prism } from '@/plugins/prism'

Vue.prototype.$util = util
Vue.prototype.$gravarar = gravarar

// 引用 fiters
const fs = filters
Object.keys(filters).forEach((key) => Vue.filter(key, fs[key]))

// 引用clickOutside
Vue.directive('click-outside', clickOutside)
// 引用prism
Vue.directive('prism', prism)
