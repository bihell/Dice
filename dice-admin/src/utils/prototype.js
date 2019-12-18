import initRules from './initRules'
import initData from './initData'
import serverConfig from './server-config'
import DiceUtil from './dice'
import dayjs from 'dayjs'
export default {
  install(Vue, options) {
    Vue.prototype.$initRules = initRules
    Vue.prototype.$initData = initData
    Vue.prototype.$util = DiceUtil.FUNCTIONS
    Vue.prototype.$serverConfig = serverConfig
    Vue.prototype.$dayjs = dayjs
    Vue.prototype.$static = DiceUtil.STATIC
  }
}
