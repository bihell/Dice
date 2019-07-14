/************************************************************/
/**
 *  全局常量
 */
const STATIC = {
  DEFAULT_CATEGORY: '默认分类',
  STATUS_PUBLISH: 'publish',
  STATUS_DRAFT: 'draft',
  META_CATEGORY: 'category',
  META_TAG: 'tag',
  ASSESS_AGREE: 'agree',
  ASSESS_DISAGREE: 'disagree'
}

/************************************************************/

/**
 * 通用工具类
 */

/**
 * 标签转字符串
 * @param tags
 * @returns {string}
 */
function tagsToString(tags) {
  if (tags.length === 0) {
    return ''
  }
  let str = ''
  for (let i = 0; i < tags.length; i++) {
    str += tags[i] + ','
  }
  return str.substr(0, str.length - 1)
}

/**
 * 字符串转标签
 * @param str
 * @returns {Array}
 */
function stringToTags(str) {
  if (!str) {
    return []
  }
  return str.toString().split(',')
}

/**
 * 跳转到指定id元素处
 * @param id 元素id
 * @param offset 偏移量
 * @param speed 速度
 */
function goAnchor(id, offset, speed) {
  const anchor = document.getElementById(id)
  const targetY = anchor.getBoundingClientRect().top
  const initialY = window.pageYOffset
  offset = offset || 0
  const position = targetY + initialY - offset
  jump(position, speed)
}

/**
 * 跳转到指定position
 * @param position
 * @param speed
 */
function jump(position, speed) {
  let distance = document.documentElement.scrollTop || document.body.scrollTop
  speed = speed || 100
  let step = position / speed
  if (position > distance) {
    smoothDown()
  } else {
    const newTotal = distance - position
    step = newTotal / speed
    smoothUp()
  }

  function smoothDown() {
    if (distance < position) {
      distance += step
      document.body.scrollTop = distance
      document.documentElement.scrollTop = distance
      setTimeout(smoothDown, 1)
    } else {
      document.body.scrollTop = position
      document.documentElement.scrollTop = position
    }
  }

  function smoothUp() {
    if (distance > position) {
      distance -= step
      document.body.scrollTop = distance
      document.documentElement.scrollTop = distance
      setTimeout(smoothUp, 1)
    } else {
      document.body.scrollTop = position
      document.documentElement.scrollTop = position
    }
  }
}

/**
 * 去除url最后的斜杠
 * @param url
 */
function formatWebsite(url) {
  if (!url || url === '') {
    return ''
  }
  if (url.charAt(url.length - 1) === '/') {
    return url.substr(0, url.length - 1)
  }
  return url
}

export default {
  STATIC,
  tagsToString,
  stringToTags,
  goAnchor,
  jump,
  formatWebsite
}
