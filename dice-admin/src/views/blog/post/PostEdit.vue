<template>
  <div class="app-container">
    <a-row :gutter="12">
      <a-col :span="24">
        <div class="mb-2">
          <a-input size="large" placeholder="请输入文章标题" :value="title" @input="setTitle" />
        </div>

        <MarkDown :value="content" :height="contentHeight" @change="setContent" />
      </a-col>
    </a-row>
    <PageFooter>
      <template #right>
        <a-button class="mr-2" type="dashed" @click="savePost('DRAFT')"> 保存草稿 </a-button>
        <a-button class="mr-2" @click="preview" disabled> 预览 </a-button>
        <a-button class="mr-2" type="primary" @click="postSetting"> 发布 </a-button>
        <a-button class="mr-2" @click="media" disabled> 媒体库 </a-button>
      </template>
    </PageFooter>
    <ArticleDrawer @register="register1" />
  </div>
</template>

<script>
  import { MarkDown } from '/@/components/Markdown';
  import { computed, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { PageFooter } from '/@/components/Page';
  import { store } from '../store';
  import { useDrawer } from '/@/components/Drawer';
  import ArticleDrawer from './PostDrawer.vue';

  export default {
    components: { MarkDown, PageFooter, ArticleDrawer },
    setup() {
      const [register1, { openDrawer: openDrawer1 }] = useDrawer();
      const contentHeight = computed(() => {
        return document.documentElement.clientHeight - 150;
      });

      const route = useRoute();

      // const fetchPost = async () => {
      //   if (route.query.id) {
      //     const post = await apiGetPost(Number(route.query.id));
      //     store.setCurrentPost(post);
      //   } else {
      //     store.setCurrentPost({});
      //   }
      // };

      const setTitle = (evt) => {
        store.setTitle(evt.target.value);
      };

      function setContent(v) {
        store.setContent(v);
      }

      function savePost(v) {
        store.savePost(v);
      }

      // todo
      function preview() {}

      // todo
      function media() {}

      function postSetting() {
        openDrawer1(true);
      }

      onMounted(() => {
        {
          store.fetchPost(route.query.id);
          // fetchPost();
        }
      });

      return {
        content: computed(() => store.state.currentPost.content),
        title: computed(() => store.state.currentPost.title),
        contentHeight,
        setContent,
        setTitle,
        savePost,
        preview,
        media,
        postSetting,
        register1,
      };
    },
  };
</script>

<style scoped>
  .app-container {
    padding: 6px;
  }
</style>
