<template>
  <div class="app-container">
    <Row :gutter="12">
      <Col :span="24">
        <div class="mb-2">
          <a-input size="large" placeholder="请输入页面标题" :value="title" @input="setPageTitle" />
        </div>

        <MarkDown
          v-model:value="value"
          :height="contentHeight"
          @change="setPageContent"
          placeholder="请输入内容"
        />
      </Col>
    </Row>
    <PageFooter>
      <template #right>
        <div class="components-input-demo-size">
          开启评论
          <Switch
            class="mr-2"
            v-model:checked="comment"
            checked-children="是"
            un-checked-children="否"
            default-checked
            @change="setPageComment"
          />
          排序权重
          <InputNumber
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

<script lang="ts">
  import { MarkDown } from '/@/components/Markdown';
  import { defineComponent, computed, onMounted, ref } from "vue";
  import { useRoute } from 'vue-router';
  import { PageFooter } from '/@/components/Page';
  import { store } from '../store';
  import { Row, Col, InputNumber, Switch } from 'ant-design-vue';

  export default defineComponent({
    components: { MarkDown, PageFooter, Row, Col, InputNumber, Switch },
    setup() {
      const contentHeight = computed(() => {
        return document.documentElement.clientHeight - 185;
      });

      const route = useRoute();

      const valueRef = ref('');

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

      onMounted(async () => {
        {
          await store.fetchPage(route.query.id);
          valueRef.value = store.state.currentPage.content
        }
      });

      return {
        // content: computed(() => store.state.currentPage.content),
        value: valueRef,
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
  });
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
