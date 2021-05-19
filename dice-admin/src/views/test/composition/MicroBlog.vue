<template>
  <card v-for="post in filteredPosts">
    <template #title>
      {{ post.title }}
    </template>
    <template #content>
      {{ post.content }}
    </template>
    <template #description>
      <Controls :post="post" @setHashtag="setHashtag" />
    </template>
  </card>
</template>

<script>
  import { ref, computed } from 'vue';
  import { store } from './store';
  import Card from '../pokemon/Card.vue';
  import Controls from './Controls.vue';

  export default {
    components: {
      Controls,
      Card,
    },
    setup() {
      const currentHashtag = ref();
      const setHashtag = (tag) => {
        currentHashtag.value = tag;
      };

      const filteredPosts = computed(() => {
        if (!currentHashtag.value) {
          return store.state.posts;
        }
        return store.state.posts.filter((post) => {
          return post.hashtags.includes(currentHashtag.value);
        });
      });

      return {
        filteredPosts,
        store,
        setHashtag,
        currentHashtag,
      };
    },
  };
</script>

<style scoped></style>
