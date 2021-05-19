import { testPosts } from '/@/views/test/composition/TestPosts';
const delay = () => new Promise((res) => setTimeout(res, 1000));

export const test = {
  namespaced: true,
  state() {
    return {
      count: 0,
      currentPost: null,
    };
  },
  // change
  mutations: {
    increment(state, payload) {
      state.count += payload.number;
    },
    setPost(state, post) {
      state.currentPost = post;
    },
  },

  // 获取数据
  actions: {
    async fetchDataFromServer(ctx, id) {
      await delay();
      const post = testPosts.find((post) => {
        return post.id === id;
      });
      // console.log(post);
      ctx.commit('setPost', post);
    },
  },
};
