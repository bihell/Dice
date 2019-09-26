import axios from 'axios'
import qs from 'qs'
import { MessageBox, Message, Loading } from 'element-ui'
import store from '@/store'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API + '/v1/api/', // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
let loadingInstance = null

function setTokenToHeader(config) {
  // set token
  const token = store.getters.token
  if (token && token.access_token) {
    config.headers['Authorization'] = token.access_token
  }
}
service.interceptors.request.use(
  config => {
    // do something before request is sent
    if (loadingInstance === null) {
      loadingInstance = Loading.service({ target: '#router-main', fullscreen: false })
    }

    setTokenToHeader(config)

    if (
      config.method === 'post' ||
      config.method === 'put' ||
      config.method === 'delete'
    ) {
      // 序列化
      config.data = qs.stringify(config.data)
    }

    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    // 处理响应数据
    if (loadingInstance !== null) {
      loadingInstance.close()
      loadingInstance = null
    }

    const res = response.data

    // if the custom code is not 20000, it is judged as an error.
    if (!res.success) {
      if (res.code !== 999) {
        Message({
          message: res.msg || 'Error',
          type: 'error',
          duration: 5 * 1000
        })
      } else {
        // to re-login
        MessageBox.confirm('你已经登出，可以按「取消」按钮停留在此页面或者重新登录。', '登出提示', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    // 处理响应失败
    if (loadingInstance !== null) {
      loadingInstance.close()
      loadingInstance = null
    }
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

/**
 * get 请求方法
 * @param url
 * @param params
 * @returns {Promise}
 */
export function get(url, params = {}) {
  return new Promise((resolve, reject) => {
    service.get(url, {
      params: params
    })
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}

/**
 * post 请求方法
 * @param url
 * @param params
 * @returns {Promise}
 */
export function post(url, params = {}) {
  return new Promise((resolve, reject) => {
    service.post(url, params).then(
      response => {
        resolve(response.data)
      },
      err => {
        reject(err)
      }
    )
  })
}

/**
 * put方法
 * @param url
 * @param params
 * @returns {Promise}
 */
export function put(url, params = {}) {
  return new Promise((resolve, reject) => {
    service.put(url, params).then(
      response => {
        resolve(response.data)
      },
      err => {
        reject(err)
      }
    )
  })
}

/**
 * delete方法
 * @param url
 * @param params
 * @returns {Promise}
 */
export function del(url, params = {}) {
  return new Promise((resolve, reject) => {
    service.delete(url, {
      params: params
    })
      .then(response => {
        resolve(response.data)
      })
      .catch(err => {
        reject(err)
      })
  })
}

export default service
