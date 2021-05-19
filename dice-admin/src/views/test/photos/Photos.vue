<template>
  <layout>
    <template v-slot:header> Header </template>
    <template v-slot:sidebar>
      <album v-for="album in albums" :album="album"> </album>
    </template>
    <template v-slot:content>
      <img v-for="photo in currentPhotos" :src="photo.thumbnailUrl" />
    </template>
  </layout>
</template>

<script>
  import Album from './Album.vue';
  import Layout from './Layout.vue';
  export default {
    components: { Layout, Album },

    computed: {
      currentPhotos() {
        return this.$store.state.photos.currentAlbumPhotos;
      },
      albums() {
        return this.$store.state.albums.all;
      },
    },

    async created() {
      this.$store.dispatch('albums/fetchAlbums');
    },
  };
</script>

<style scoped>
  * {
    box-sizing: border-box;
  }

  body {
    margin: 0;
  }
</style>
