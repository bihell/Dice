<template>
  <BasicTable @register="registerTable">
    <template #action="{ record }">
      <TableAction
        :drop-down-actions="[
          {
            label: '明细',
            onClick: handleDetailClick.bind(null, record),
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
  import { getCommentColumns } from '../tableData';
  import { apiCommentList, apiDeleteComment } from '/@/api/blog/blog';
  import { FormOutlined } from '@ant-design/icons-vue';
  import { useMessage } from '/@/hooks/web/useMessage';

  export default defineComponent({
    components: { BasicTable, TableAction, FormOutlined },
    setup() {
      const { createMessage, createConfirm } = useMessage();
      const { success } = createMessage;
      const [registerTable, { reload }] = useTable({
        title: '评论列表',
        api: apiCommentList,
        columns: getCommentColumns(),
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

      async function handleDeleteClick(record: any) {
        createConfirm({
          iconType: 'warning',
          title: '删除评论',
          content: '确定要删除么？',
          onOk: async () => {
            await apiDeleteComment(record.id);
            success('删除评论成功');
            await reload();
          },
        });
      }

      function handleDetailClick(record: any) {
        console.log(record.id);
        createConfirm({
          iconType: 'info',
          title: '功能开发中',
          content: '正在开发中，欢迎提PR',
        });
      }

      return {
        registerTable,
        handleDeleteClick,
        handleDetailClick,
      };
    },
  });
</script>

<style lang="less" scoped>
  .border-none {
    border: 0 !important;
  }
</style>
