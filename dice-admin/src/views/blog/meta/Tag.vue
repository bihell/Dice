<template>
  <div class="app-container">
    <a-row :gutter="30">
      <a-col :xs="24" :sm="12" :md="12" :lg="12">
        <a-card title="标签列表">
          <ul class="meta-list">
            <li v-for="tag in tagList" :key="tag.id">
              <span class="meta" @click="handleTagClick(tag)">{{ tag.name }} </span>
              <span style="float: right; clear: both;">
                <span class="radius-count">{{ tag.count }}</span>
                <a-button type="danger" @click="handleDeleteMetaClick(tag.name, 'tag')"
                  >删除</a-button
                >
              </span>
            </li>
          </ul>
          <a-input v-model:value.trim="tag.name" placeholder="请输入标签名称" class="meta-input" />
          <a-button style="float: right; clear: both;" @click="handleSaveOrUpdateTagClick">
            保存标签
          </a-button>
        </a-card>
      </a-col>
      <a-col :xs="24" :sm="12" :md="12" :lg="12">
        <a-card title="分类列表">
          <ul class="meta-list">
            <li v-for="category in categoryList" :key="category.id">
              <span class="meta" @click="handleCategoryClick(category)">{{ category.name }} </span>
              <span style="float: right; clear: both;">
                <span class="radius-count">{{ category.count }}</span>
                <a-button type="danger" @click="handleDeleteMetaClick(category.name, 'category')"
                  >删除</a-button
                >
              </span>
            </li>
          </ul>
          <a-input
            v-model:value.trim="category.name"
            placeholder="请输入标签名称"
            class="meta-input"
          />
          <a-button style="float: right; clear: both;" @click="handleSaveOrUpdateCategoryClick">
            保存标签
          </a-button>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script lang="ts">
  import { store } from '../store';
  import { computed, reactive } from 'vue';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { apiSaveMeta, apiUpdateMeta, apiDeleteMeta, apiDeletePost } from '/@/api/blog/blog';
  const { createMessage, createConfirm, createErrorModal } = useMessage();
  const { success } = createMessage;
  export default {
    setup() {
      store.fetchMetaList();
      const tag = reactive({
        name: '',
        id: '',
      });
      const category = reactive({
        name: '',
        id: '',
      });

      function handleTagClick(v: any) {
        tag.name = v.name;
        tag.id = v.id;
      }

      function handleCategoryClick(v: any) {
        category.name = v.name;
        category.id = v.id;
      }

      function handleDeleteMetaClick(name: string, type: string) {
        createConfirm({
          iconType: 'warning',
          title: '删除确认',
          content: '确定要删除么？',
          onOk: async () => {
            await apiDeleteMeta(name, type);
            success('已删除');
            await store.fetchMetaList();
          },
        });
      }

      async function handleSaveOrUpdateTagClick() {
        if (tag.name === '') {
          createErrorModal({ title: 'Tip', content: '标签名不能为空' });
          return;
        }
        if (tag.id !== '') {
          await apiUpdateMeta(Number(tag.id), tag.name, 'tag');
          success('更新标签成功');
          tag.name = '';
          tag.id = '';
          await store.fetchMetaList();
        } else {
          await apiSaveMeta(tag.name, 'tag');
          success('新增标签成功');
          tag.name = '';
          tag.id = '';
          await store.fetchMetaList();
        }
      }

      async function handleSaveOrUpdateCategoryClick() {
        if (category.name === '') {
          createErrorModal({ title: 'Tip', content: '分类名不能为空' });
          return;
        }
        if (category.id !== '') {
          await apiUpdateMeta(Number(category.id), category.name, 'category');
          success('更新分类成功');
          category.name = '';
          category.id = '';
          await store.fetchMetaList();
        } else {
          await apiSaveMeta(category.name, 'category');
          success('新增分类成功');
          category.name = '';
          category.id = '';
          await store.fetchMetaList();
        }
      }

      return {
        tagList: computed(() => store.state.tagList),
        categoryList: computed(() => store.state.categoryList),
        tag,
        category,
        handleTagClick,
        handleCategoryClick,
        handleDeleteMetaClick,
        handleSaveOrUpdateTagClick,
        handleSaveOrUpdateCategoryClick,
      };
    },
  };
</script>

<style scoped>
  .app-container {
    padding: 6px;
  }

  .meta-list {
    padding: 0;
    margin: 0 0 30px 0;
    list-style: none;
  }

  .meta-list li {
    line-height: 2.4rem;
  }

  .meta-list .meta {
    max-width: 350px;
    padding: 0.4rem 0.5rem;
    margin: 0.4rem;
    font-size: 14px;
    font-weight: 600;
    color: #333;
    white-space: nowrap;
    cursor: pointer;
    background-color: #ffd740;
    border: 1px solid #ffd740;
    box-shadow: 0 0 3px rgba(14, 14, 14, 0.3);
  }

  .meta-list .meta:hover {
    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    transition: all 0.2s;
  }

  .meta-list .meta:active {
    box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
  }

  .meta-input {
    display: inline-block;
    width: 200px;
    margin-left: 5px;
  }

  .clearfix::before,
  .clearfix::after {
    display: table;
    content: '';
  }

  .clearfix::after {
    clear: both;
  }

  .radius-count {
    display: inline-block;
    min-width: 10px;
    padding: 4px 7px;
    margin-right: 20px;
    font-size: 11px;
    font-weight: 700;
    line-height: 12px;
    color: #fff;
    text-align: center;
    white-space: nowrap;
    vertical-align: baseline;
    background-color: #f36a5a;
    border-radius: 10px;
  }
</style>
