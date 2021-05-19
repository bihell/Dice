<template>
  <div class="app-container">
    <a-tabs tab-position="left" @change="callback">
      <a-tab-pane key="1" tab="博客设置">
        <BasicForm layout="vertical" @register="blogSetRegister" />
        <a-button type="primary" @click="saveSetting(1)">保存博客设置</a-button>
      </a-tab-pane>
      <a-tab-pane key="2" tab="SEO 设置">
        <BasicForm layout="vertical" @register="seoSetRegister" />
        <a-button type="primary" @click="saveSetting(2)">保存SEO设置</a-button>
      </a-tab-pane>
      <a-tab-pane key="3" tab="邮箱设置">
        <BasicForm layout="vertical" @register="mailSetRegister" />
        <a-button type="primary" @click="saveSetting(3)">保存邮箱设置</a-button>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script lang="ts">
  import { defineComponent, onMounted, nextTick } from 'vue';
  import { blogSetSchemas, seoSetSchemas, mailSetSchemas } from '../tableData';
  import { BasicForm, useForm } from '/@/components/Form';
  import { store } from '../store';

  export default defineComponent({
    components: {
      BasicForm,
    },
    setup() {
      const [
        blogSetRegister,
        { setFieldsValue: baseSetting, getFieldsValue: getBaseSetting },
      ] = useForm({
        labelWidth: 120,
        schemas: blogSetSchemas,
        showActionButtonGroup: false,
      });
      const [
        seoSetRegister,
        { setFieldsValue: seoSetting, getFieldsValue: getSeoSetting },
      ] = useForm({
        labelWidth: 500,
        schemas: seoSetSchemas,
        showActionButtonGroup: false,
      });
      const [
        mailSetRegister,
        { setFieldsValue: mailSetting, getFieldsValue: getMailSetting },
      ] = useForm({
        labelWidth: 500,
        schemas: mailSetSchemas,
        showActionButtonGroup: false,
      });

      onMounted(async () => {
        await store.fetchBlogSetting();
        await baseSetting(store.state.blogSetting);
      });

      async function callback(key: string) {
        if (key === '1') {
          await baseSetting(store.state.blogSetting);
        }
        if (key === '2') {
          await nextTick();
          await seoSetting(store.state.blogSetting);
        }
        if (key === '3') {
          await nextTick();
          await mailSetting(store.state.blogSetting);
        }
      }

      function saveSetting(tabId: number) {
        switch (tabId) {
          case 1:
            store.saveSetting(getBaseSetting());
            break;
          case 2:
            store.saveSetting(getSeoSetting());
            break;
          case 3:
            store.saveSetting(getMailSetting());
            break;
        }
      }
      return {
        blogSetRegister,
        seoSetRegister,
        mailSetRegister,
        callback,
        saveSetting,
        tabBarStyle: {
          width: '220px',
        },
      };
    },
  });
</script>
<style lang="less">
  .app-container {
    padding: 6px;
    background: #fff;

    .ant-tabs-tab-active {
      background-color: #e6f7ff;
    }
  }
</style>
