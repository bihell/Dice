<template>
  <div class="app-container">
    <a-row :gutter="12">
      <a-col :span="24">
        <div class="mb-2">
          <a-input size="large" placeholder="请输入页面标题" :value="title" @input="setPageTitle" />
        </div>

        <MarkDown :value="content" :height="contentHeight" @change="setPageContent" />
      </a-col>
    </a-row>
    <PageFooter>
      <template #right>
        <div class="components-input-demo-size">
          开启评论
          <a-switch
            class="mr-2"
            v-model:checked="comment"
            checked-children="是"
            un-checked-children="否"
            default-checked
            @change="setPageComment"
          />
          排序权重
          <a-input-number
            class="mr-2"
            id="inputNumber"
            v-model:value="priority"
            :min="0"
            @change="setPagePriority"
          />
          <a-button class="mr-2" type="dashed" @click="savePage('DRAFT')"> 保存草稿 </a-button>
          <a-button class="mr-2" disabled @click="preview"> 预览 </a-button>
          <a-button class="mr-2" type="primary" @click="savePage('PUBLISHED')"> 发布 </a-button>
          <a-button class="mr-2" disabled @click="media"> 媒体库 </a-button>
        </div>
      </template>
    </PageFooter>
  </div>
</template>

<script>
  import { MarkDown } from '/@/components/Markdown';
  import { computed, onMounted } from 'vue';
  import { useRoute } from 'vue-router';
  import { PageFooter } from '/@/components/Page';
  import { store } from '../store';

  export default {
    components: { MarkDown, PageFooter },
    setup() {
      const contentHeight = computed(() => {
        return document.documentElement.clientHeight - 185;
      });

      const route = useRoute();

      const setPageTitle = (evt) => {
        store.setPageTitle(evt.target.value);
      };

      function setPageContent(v) {
        store.setPageContent(v);
      }

      function setPagePriority(v) {
        store.setPagePriority(v);
      }

      function setPageComment(v) {
        store.setPageComment(v);
      }

      function savePage(v) {
        store.savePage(v);
      }

      // todo
      function preview() {}

      // todo
      function media() {}

      onMounted(() => {
        {
          store.fetchPage(route.query.id);
        }
      });

      return {
        content: computed(() => store.state.currentPage.content),
        title: computed(() => store.state.currentPage.title),
        priority: computed(() => store.state.currentPage.priority),
        comment: computed(() => store.state.currentPage.allowComment),
        contentHeight,
        setPageContent,
        setPageTitle,
        setPagePriority,
        setPageComment,
        savePage,
        preview,
        media,
      };
    },
  };
</script>

<style scoped>
  .app-container {
    padding: 6px;
  }

  .components-input-demo-size .ant-input {
    width: 200px;
    margin: 0 8px 8px 0;
  }
</style>
