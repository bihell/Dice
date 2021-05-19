<template>
  <div class="hashtag" @click="setHashtag"> # {{ hashtag }} </div>
</template>

<script>
  export default {
    props: {
      hashtag: {
        type: String,
      },
    },

    setup(props, ctx) {
      const setHashtag = () => {
        ctx.emit('setHashtag', props.hashtag);
      };
      return {
        setHashtag,
      };
    },
  };
</script>

<style scoped>
  .hashtag {
    text-decoration: underline;
  }

  .hashtag:hover {
    color: cornflowerblue;
  }
</style>
