import { computed, reactive } from 'vue';
import { testPosts } from './TestPosts';

class Store {
  constructor() {
    this.state = reactive({
      posts: testPosts,
      currentHashtag: null,
    });
  }

  setHashtag(tag) {
    this.state.currentHashtag = tag;
  }

  get filteredPosts() {
    return computed(() => {
      if (!this.state.currentHashtag) {
        return this.state.posts;
      }
      return this.state.posts.filter((post) => {
        return post.hashtags.includes(this.state.currentHashtag);
      });
    });
  }

  incrementLike(post) {
    const thePost = this.state.posts.find((x) => x.id === post.id);

    if (!thePost) {
      return;
    }
    thePost.likes += 1;
  }
}
export const store = new Store();
