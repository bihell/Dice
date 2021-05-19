<template>
  <input type="text" :value="currentHashtag" @input="setHashtag" />
  <card v-for="post in filteredPosts">
    <template #title>
      {{ post.title }}
    </template>
    <template #content>
      {{ post.content }}
    </template>
    <template #description>
      <Controls :post="post" />
    </template>
  </card>
</template>

<script>
  import { computed } from 'vue';
  import { store } from './StoreV2';
  import Card from '../pokemon/Card.vue';
  import Controls from './ControlsV2.vue';

  export default {
    components: {
      Controls,
      Card,
    },
    setup() {
      const setHashtag = (evt) => {
        store.setHashtag(evt.target.value);
      };

      return {
        filteredPosts: store.filteredPosts,
        setHashtag,
        currentHashtag: computed(() => store.state.currentHashtag),
      };
    },
  };
</script>

<style scoped></style>
