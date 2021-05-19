<template>
  <div>
    <button>
      {{ post.likes }}
    </button>
    <Hashtag v-for="tag in post.hashtags" :hashtag="tag" @setHashtag="setHashtag" />
  </div>
</template>

<script>
  import Hashtag from './Hashtag.vue';
  export default {
    components: { Hashtag },
    props: {
      post: {
        type: Object,
      },
    },

    setup(props, ctx) {
      const setHashtag = (tag) => {
        ctx.emit('setHashtag', tag);
      };

      return {
        setHashtag,
      };
    },
  };
</script>

<style scoped></style>
