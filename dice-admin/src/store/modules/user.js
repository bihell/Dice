import { login, logout, getAllUsers } from '@/api/auth'
import { getUserInfo } from '@/api/oneAuth'
import router, { resetRouter } from '@/router'
import Vue from 'vue'

const state = {
  token: null,
  name: '',
  avatar: '',
  users: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    Vue.ls.set('Access-Token', token)
    state.token = token
  },
  CLEAR_TOKEN: state => {
    Vue.ls.remove('Access-Token')
    state.token = null
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_USERS: (state, users) => {
    state.users = users
  }
}

const actions = {
  // user login
  // 只验证登录，权限控制写死
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        commit('SET_TOKEN', response.data)
        // setToken('admin-token')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  setCurrentToken({ commit }) {
    commit('SET_TOKEN', Vue.ls.get('Access-Token'))
  },

  // 获取用户信息
  // todo 目前AVATAR 写死以后增加对应字段
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getUserInfo().then(res => {
        if (res.data) {
          commit('SET_NAME', res.data.screenName)
          commit('SET_AVATAR', '/admin/avatar.jpeg')
        }
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('CLEAR_TOKEN')
        commit('SET_NAME', null)

        // reset visited views and cached views
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, { root: true })
        resetRouter()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      resolve()
    })
  },

  // dynamically modify permissions
  changeRoles({ commit, dispatch }, role) {
    return new Promise(async resolve => {
      const token = role + '-token'

      commit('SET_TOKEN', token)
      // setToken(token)

      const { roles } = await dispatch('getInfo')

      resetRouter()

      // generate accessible routes map based on roles
      const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })

      // dynamically add accessible routes
      router.addRoutes(accessRoutes)

      // reset visited views and cached views
      dispatch('tagsView/delAllViews', null, { root: true })

      resolve()
    })
  },

  getAllUsers({ commit, state }) {
    return new Promise((resolve, reject) => {
      getAllUsers().then(res => {
        if (res.data && res.data.list) {
          const data = res.data.list
          data.forEach(item => {
            const prefix = item.id < 0 ? '【ToB】' : '【普通用户】'
            item.username = `${prefix}${item.username}`
          })
          commit('SET_USERS', data)
        }
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
