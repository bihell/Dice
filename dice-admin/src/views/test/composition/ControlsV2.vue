<template>
  <div>
    <div class="like">
      <button @click="like"> Like </button>
      <div>
        {{ post.likes }}
      </div>
    </div>

    <Hashtag v-for="tag in post.hashtags" :hashtag="tag" />
  </div>
</template>

<script>
  import Hashtag from './HashtagV2.vue';
  import { store } from './StoreV2';
  export default {
    components: { Hashtag },
    props: {
      post: {
        type: Object,
      },
    },

    setup(props) {
      const like = () => {
        store.incrementLike(props.post);
      };

      return {
        like,
      };
    },
  };
</script>

<style scoped>
  .like {
    display: flex;
    justify-content: center;
    margin: 10px;
  }
</style>
