<template>
  <div>
    <BasicTable @register="registerTable">
      <template #form-userAdd>
        <a-button type="primary" @click="userAdd"> <FileAddOutlined /> 新增用户 </a-button>
      </template>
      <template #action="{ record }">
        <TableAction
          :drop-down-actions="[
            {
              label: '编辑',
              onClick: userAdd,
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
  import { getUserColumns, getUserFormConfig } from '../Data';
  import { postStatus } from '/@/api/blog/blog';
  import { apiUserList } from '/@/api/auth/auth';
  import { FormOutlined, FileAddOutlined } from '@ant-design/icons-vue';
  import { useRouter } from 'vue-router';
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
        title: '用户列表',
        api: apiUserList,
        columns: getUserColumns(),
        useSearchForm: true,
        formConfig: getUserFormConfig(),
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

      function userAdd() {
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
        status,
        handleDeleteClick,
        userAdd,
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
