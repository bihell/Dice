export const state = () => ({
  list: {
    data: [],
    totalPage: 1,
    currentPage: 1
  },
  detail: {},
  page: {},
  pageMenu: []
})

export const mutations = {
  SET_DETAIL(state, data) {
    state.detail = data
  },
  SET_LIST(state, data) {
    state.list = data
  },
  SET_PAGE(state, data) {
    state.page = data
  },
  SET_PAGE_MENU(state, data) {
    state.pageMenu = data
  }
}
