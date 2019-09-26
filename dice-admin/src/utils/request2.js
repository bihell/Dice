import axios from 'axios'
import { MessageBox, Message, Loading } from 'element-ui'
import store from '@/store'

// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API + '/v1/api/', // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  crossDomain: true,
  timeout: 5000 // request timeout
})

let loadingInstance = null

function setTokenToHeader(config) {
  // set token
  const token = store.getters.token
  if (token && token.access_token) {
    config.headers['Authorization'] = token.access_token
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

export default service
