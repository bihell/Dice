const isProd = process.env.NODE_ENV === 'production'

const devUrl = 'http://127.0.0.1:9091'
const devProxy = {
  host: '127.0.0.1',
  port: 9091,
}

const prodUrl = ''
const prodProxy = {
  protocol: 'http',
  host: 'dice-server',
  port: 9091,
}

const config = {
  isProd,
  api: isProd ? prodUrl : devUrl,
  baseProxy: isProd ? prodProxy : devProxy,
}

export default config
