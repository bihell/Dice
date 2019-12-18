const isProd = process.env.NODE_ENV === 'production'

const devUrl = 'http://127.0.0.1:9091/'
const prodUrl = '/'
const devFrontUrl = 'http://127.0.0.1:3000/'
const prodFrontUrl = '/'

const api = isProd ? prodUrl : devUrl
const frontUrl = isProd ? prodFrontUrl : devFrontUrl
export default {
  isProd,
  api,
  frontUrl
}
