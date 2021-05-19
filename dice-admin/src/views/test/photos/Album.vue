<!--<template>-->
<!--  <button @click="click">{{ album.title }} </button>-->
<!--</template>-->

<!--<script>-->
<!--  import { useStore } from 'vuex';-->

<!--  export default {-->
<!--    props: {-->
<!--      album: {-->
<!--        type: Object,-->
<!--      },-->
<!--    },-->

<!--    setup(props) {-->
<!--      const store = useStore();-->
<!--      const click = () => {-->
<!--        store.dispatch('photos/fetchPhotosForAlbum', { album: props.album });-->
<!--      };-->

<!--      return {-->
<!--        click,-->
<!--      };-->
<!--    },-->
<!--  };-->
<!--</script>-->

<!--<style scoped>-->
<!--  button {-->
<!--    width: 100%;-->
<!--    padding: 10px;-->
<!--    margin: 5px;-->
<!--    font-size: 18px;-->
<!--    color: white;-->
<!--    background: darkcyan;-->
<!--    border: none;-->
<!--    border-radius: 5px;-->
<!--    transition: 0.1s;-->
<!--  }-->

<!--  button:hover {-->
<!--    cursor: pointer;-->
<!--    filter: brightness(120%);-->
<!--    transition: 0.1s;-->
<!--  }-->
<!--</style>-->
