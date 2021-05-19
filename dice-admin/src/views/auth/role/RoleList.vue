<template>
  <div>
    <BasicTable @register="registerTable">
      <template #form-add>
        <a-button type="primary" @click="roleAdd"> <FileAddOutlined /> 新增 </a-button>
      </template>
      <template #action="{ record }">
        <TableAction
          :drop-down-actions="[
            {
              label: '编辑',
              onClick: add,
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
    <Modal1 @register="register1" />
  </div>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getRoleColumns, getRoleFormConfig } from '../Data';
  import { apiRoleList } from '/@/api/auth/auth';
  import { FormOutlined, FileAddOutlined } from '@ant-design/icons-vue';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { useModal } from '/@/components/Modal';
  import Modal1 from './Modal1.vue';

  export default defineComponent({
    components: { BasicTable, TableAction, FormOutlined, FileAddOutlined, Modal1 },
    setup() {
      const [register1, { openModal: openModal1, setModalProps }] = useModal();
      const { createMessage, createConfirm } = useMessage();
      const { success } = createMessage;
      const [registerTable, { reload }] = useTable({
        title: '角色列表',
        api: apiRoleList,
        columns: getRoleColumns(),
        useSearchForm: true,
        formConfig: getRoleFormConfig(),
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

      function add() {
        openModal1();
        setModalProps({ loading: true });
        setTimeout(() => {
          setModalProps({ loading: false });
        }, 2000);
      }

      async function handleDeleteClick(record: any) {
        createConfirm({
          iconType: 'warning',
          title: '删除用户',
          content: '功能开发中。。。。',
          // onOk: async () => {
          //   await apiDeletePost(record.id);
          //   success('文章删除成功');
          //   await reload();
          // },
        });
      }

      return {
        registerTable,
        handleDeleteClick,
        add,
        register1,
      };
    },
  });
</script>

<style lang="less" scoped>
  .border-none {
    border: 0 !important;
  }
</style>
