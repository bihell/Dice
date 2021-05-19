export const albums = {
  namespaced: true,

  state() {
    return {
      all: [],
    };
  },

  mutations: {
    setAlbums(state, payload) {
      state.all = payload;
    },
  },

  actions: {
    async fetchAlbums(ctx) {
      // dispatch action -> fetch data
      /// commit mutation -> update the state
      const res = await fetch('https://jsonplaceholder.typicode.com/albums');
      const data = await res.json();
      ctx.commit('setAlbums', data);
    },
  },
};
