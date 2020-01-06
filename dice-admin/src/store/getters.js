import _ from 'lodash'

export default {
  labels: state => {
    return state.labels.items
  },

  labelSnippets: state => {
    return state.labelSnippets.items
  },

  untagged: state => {
    return _.filter(state.snippets, v => {
      return _.isEmpty(v.label.name)
    })
  },
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.permission.roles,
  permission_routes: state => state.permission.routes,
  errorLogs: state => state.errorLog.logs,
  subSystem: state => state.permission.subSystemList

}
