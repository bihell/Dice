import { reactive } from 'vue';
import { testPosts } from './TestPosts';

class Store {
  constructor() {
    this.state = reactive({
      posts: testPosts,
    });
  }
}
export const store = new Store();
