export const photos = {
  namespaced: true,
  state() {
    return {
      currentAlbumPhotos: [],
      loading: false,
      photoCache: [],
    };
  },

  mutations: {
    setPhotos(state, { photos, albumId }) {
      state.currentAlbumPhotos = photos;
      console.log(albumId);
      state.photoCache[albumId] = photos;
    },
    setLoading(state, loading) {
      state.loading = loading;
    },
  },

  actions: {
    async fetchPhotosForAlbum(ctx, { album }) {
      if (ctx.state.photoCache[album.id]) {
        ctx.commit('setPhotos', { photos: ctx.state.photoCache[album.id], albumId: album.id });
        console.log('有缓存');
        return;
      }
      ctx.commit('setLoading', true);

      const res = await fetch(`https://jsonplaceholder.typicode.com/photos?albumId=${album.id}`);
      const data = await res.json();
      ctx.commit('setPhotos', { photos: data, albumId: album.id });

      ctx.commit('setLoading', false);
    },
  },
};
