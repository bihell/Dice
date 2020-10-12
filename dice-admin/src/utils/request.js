import axios from 'axios'
import { Message, Loading } from 'element-ui'
import store from '@/store'

// create an axios instance
const service = axios.create({
  // baseURL: process.env.VUE_APP_BASE_API,
  baseURL: '/v1/api/',
  withCredentials: true, // send cookies when cross-domain requests
  crossDomain: true,
  timeout: 5000 // request timeout
})

let loadingInstance = null

function setTokenToHeader(config) {
  // set token
  const token = store.getters.token
  if (token) {
    config.headers['Authorization'] = token
  }
}

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent

    if (loadingInstance === null) {
      loadingInstance = Loading.service({ target: '#router-main', fullscreen: false })
    }

    setTokenToHeader(config)
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
      console.log(res)
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(new Error(res.message || 'Error'))
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
    if (error.response.status === 401) {
      // to re-login
      store.dispatch('user/resetToken').then(() => {
        location.reload()
      })
    } else {
      Message({
        message: error.message,
        type: 'error',
        duration: 5 * 1000
      })
    }
    return Promise.reject(error)
  }
)

export default service
