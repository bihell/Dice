import { asyncRoutes, constantRoutes } from '@/router'
import * as authApi from '@/api/auth'
import { getMenuList } from '@/api/oneAuth'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

function getAllMenus(menus, commit) {
  const result = []
  const roles = ['no-permission']
  const thirdUrls = []
  menus && menus.forEach(project => {
    const menu_2nd = []
    project.children && project.children.forEach(kClass => {
      const url = project.group_url + kClass.classes_url
      menu_2nd.push({ displayName: kClass.classes_name, path: kClass.classes_url, url })
      kClass.children && kClass.children.forEach(item => {
        roles.push(item.item_code)
        if (item.outer_url) {
          thirdUrls.push({
            url,
            src: item.outer_url
          })
        }
      })
    })
    const menu_1st = { displayName: project.group_name, child: menu_2nd, url: project.group_url, icon: project.icon ? project.style : null }
    result.push(menu_1st)
  })
  commit('SET_ROLES', roles)
  commit('SET_THIRD_URLS', thirdUrls)
  return result
}

function getMenus(menus) {
  const result = []
  menus && menus.forEach(project => {
    if (project.is_display) {
      const menu_2nd = []
      project.children && project.children.forEach(kClass => {
        if (kClass.is_display) {
          const url = project.group_url + kClass.classes_url
          menu_2nd.push({ displayName: kClass.classes_name, path: kClass.classes_url, url })
        }
      })
      const menu_1st = { displayName: project.group_name, child: menu_2nd, url: project.group_url, icon: project.style ? project.style : null }
      result.push(menu_1st)
    }
  })
  return result
}

const state = {
  routes: [],
  addRoutes: [],
  allSubSystemList: [],
  roles: [],
  thirdUrls: [],
  menuList: [],
  allMenus: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  },
  SET_ALL_SUB_SYSTEM: (state, subSystem) => {
    state.allSubSystemList = subSystem
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_THIRD_URLS: (state, thirdUrls) => {
    state.thirdUrls = thirdUrls
  },
  SET_MENU: (state, menus) => {
    state.menuList = menus
  },
  SET_ALL_MENU: (state, allMenus) => {
    state.allMenus = allMenus
  }
}

// todo 这里直接写死路由，后续打算用权限系统直接生成菜单。
const actions = {
  generateRoutes({ commit }, roles) {
    return new Promise(resolve => {
      const accessedRoutes = asyncRoutes || []
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  },
  getCurrentMenu({ commit }) {
    return new Promise((resolve, reject) => {
      getMenuList().then(res => {
        if (res.data) {
          const menus = getMenus(res.data)
          const allMenus = getAllMenus(res.data, commit)
          commit('SET_MENU', menus)
          commit('SET_ALL_MENU', allMenus)
          resolve(menus)
        } else {
          resolve([])
        }
      }).catch(error => {
        reject(error)
      })
    })
  },
  getAllSubSystem({ commit }) {
    return new Promise((resolve, reject) => {
      authApi.getAllSubSystem().then(res => {
        if (res.data) {
          const result = res.data
          const subSystem = (result && result.length) ? result : []
          commit('SET_ALL_SUB_SYSTEM', subSystem)
          resolve()
        } else {
          resolve([])
        }
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
