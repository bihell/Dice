import { Message } from 'element-ui'
import serverConfig from './server-config'

/** **********************************************************/
/**
 *  全局常量
 */
const STATIC = {
  DEFAULT_CATEGORY: '默认分类',
  STATUS_PUBLISH: 'publish',
  STATUS_DRAFT: 'draft',
  META_CATEGORY: 'category',
  META_TAG: 'tag',
  META_SNIPPET_TAG: 'snippet_tag',
  DEFAULT_PAGE_SIZE: 13
}

/** **********************************************************/

/**
 * 通用工具类
 */
const FUNCTIONS = {
  /**
   * 获取服务器链接
   * @returns {string}
   */
  getServerUrl: () => {
    return serverConfig.api
  },
  /**
   * 获取服务器媒体链接
   * @param url
   * @returns {string}
   */
  getServerMediaUrl: url => {
    return FUNCTIONS.getServerUrl() + 'media/' + url
  },
  /**
   * 标签转字符串
   * @param tags
   * @returns {string}
   */
  tagsToString: tags => {
    let str = ''
    for (let i = 0; i < tags.length; i++) {
      str += tags[i] + ','
    }
    return str.substr(0, str.length - 1)
  },
  /**
   * 字符串转标签
   * @param str
   * @returns {Array}
   */
  stringToTags: str => {
    if (str !== null && str !== '') {
      return str.split(',')
    } else {
      return []
    }
  },
  /**
   * 复制文字到剪切板
   * @param text
   */
  copyText: text => {
    const oInput = document.createElement('input')
    oInput.value = text
    document.body.appendChild(oInput)
    oInput.select() // 选择对象
    document.execCommand('Copy') // 执行浏览器复制命令
    oInput.className = 'oInput'
    oInput.style.display = 'none'
  },
  /**
   * 通用提示信息
   * @type {{success: message.success, warning: message.warning, error: message.error, info: message.info}}
   */
  message: {
    success: function(message) {
      Message({
        showClose: true,
        message: message || '成功',
        type: 'success'
      })
    },
    warning: function(message) {
      Message({
        showClose: true,
        message: message || '警告',
        type: 'warning'
      })
    },
    info: function(message) {
      Message({
        showClose: true,
        message: message || '提示'
      })
    },
    error: function(message) {
      Message({
        showClose: true,
        message: message || '异常',
        type: 'error'
      })
    }
  }
}

export default {
  STATIC,
  FUNCTIONS
}
