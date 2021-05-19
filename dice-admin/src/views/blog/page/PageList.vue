<template>
  <BasicTable @register="registerTable">
    <template #form-advanceBefore>
      <router-link :to="{ name: 'PageEdit' }">
        <a-button type="primary"> <FileAddOutlined /> 新建页面</a-button>
      </router-link>
    </template>
    <template #category="{ record }">
      <Tag color="blue">
        {{ record.category }}
      </Tag>
    </template>
    <template #status="{ record }">
      <Tag color="blue">
        {{ status[record.status].text }}
      </Tag>
    </template>
    <template #cc="{ record }">
      <Badge :count="record.commentCount" show-zero />
    </template>
    <template #action="{ record }">
      <TableAction
        :drop-down-actions="[
          {
            label: '编辑',
            onClick: handleEditClick.bind(null, record),
          },
          {
            label: '删除',
            onClick: handleDeleteClick.bind(null, record),
          },
        ]"
        :divider="false"
      >
        <template #more>
          <a-button shape="circle" class="border-none">
            <FormOutlined />
          </a-button>
        </template>
      </TableAction>
    </template>
  </BasicTable>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getPageColumns, getPageConfig } from '../tableData';
  import { Tag, Badge } from 'ant-design-vue';
  import { apiPageList, postStatus, apiDeletePage } from '/@/api/blog/blog';
  import { FormOutlined, FileAddOutlined } from '@ant-design/icons-vue';
  import { useRouter } from 'vue-router';
  import { useMessage } from '/@/hooks/web/useMessage';

  export default defineComponent({
    components: { BasicTable, Tag, Badge, TableAction, FormOutlined, FileAddOutlined },
    setup() {
      const { createMessage, createConfirm } = useMessage();
      const { success } = createMessage;
      const [registerTable, { reload }] = useTable({
        title: '页面列表',
        api: apiPageList,
        columns: getPageColumns(),
        useSearchForm: true,
        formConfig: getPageConfig(),
        showTableSetting: true,
        showIndexColumn: false,
        bordered: true,
        actionColumn: {
          width: 50,
          title: '操作',
          align: 'center',
          dataIndex: 'action',
          slots: { customRender: 'action' },
        },
      });

      const status = postStatus();
      const router = useRouter();

      function pushWithQuery(query: any) {
        router.push({
          name: 'PageEdit',
          query: query,
        });
      }

      function handleEditClick(record: any) {
        pushWithQuery({ id: record.id });
      }

      async function handleDeleteClick(record: any) {
        createConfirm({
          iconType: 'warning',
          title: '删除确认',
          content: '确定要删除页面么？',
          onOk: async () => {
            await apiDeletePage(record.id);
            success('页面删除成功');
            await reload();
          },
        });
      }

      return {
        registerTable,
        status,
        handleEditClick,
        handleDeleteClick,
      };
    },
  });
</script>

<style lang="less" scoped>
  .border-none {
    border: 0 !important;
  }
</style>
