<template>
  <BasicDrawer
    v-bind="$attrs"
    @register="registerDrawer"
    showFooter
    :title="getTitle"
    width="500px"
    @ok="handleSubmit"
  >
    <BasicForm @register="registerForm">
      <template #menu="{ model, field }">
        <BasicTree
          v-model:value="model[field]"
          :treeData="treeData"
          :fieldNames="{ title: 'name', key: 'id' }"
          :checkedKeys="checkedKeys"
          :selectedKeys="selectedKeys"
          @check="handleCheck"
          checkable
          toolbar
          title="权限分配"
        />
      </template>
    </BasicForm>
  </BasicDrawer>
</template>
<script lang="ts">
  import { defineComponent, ref, computed, unref } from 'vue';
  import { BasicForm, useForm } from '/@/components/Form/index';
  import { formSchema } from './role.data';
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  import { BasicTree, TreeItem } from '/@/components/Tree';

  import { addRole, getMenuTreeList, updateRolePermission } from '/@/api/sys/system';

  import { listRoleMenus } from '/@/api/auth/sysRole';
  import { getMenuList } from '/@/api/sys/menu';

  export default defineComponent({
    name: 'RoleDrawer',
    components: { BasicDrawer, BasicForm, BasicTree },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const isUpdate = ref(true);
      const treeData = ref<TreeItem[]>([]);
      const roleId = ref(null);
      const checkedKeys = ref([]);
      const selectedKeys = ref([]);
      const parentIds = ref(new Set());
      const halfCheckedKeys = ref([]);
      const treeCheck = ref(false);

      const [registerForm, { resetFields, setFieldsValue, validate }] = useForm({
        labelWidth: 90,
        baseColProps: { span: 24 },
        schemas: formSchema,
        showActionButtonGroup: false,
      });

      const [registerDrawer, { setDrawerProps, closeDrawer }] = useDrawerInner(async (data) => {
        resetFields();
        setDrawerProps({ confirmLoading: false });
        isUpdate.value = !!data?.isUpdate;
        treeData.value = (await getMenuTreeList()) as any as TreeItem[];
        if (unref(isUpdate)) {
          setFieldsValue({
            ...data.record,
          });
          roleId.value = data.record.id;

          console.log(getMenuList());

          const roleMenuList = (await listRoleMenus({ roleId: roleId.value })) as any;

          // 找出菜单的所有父节点ID
          parentIds.value = new Set(
            roleMenuList.filter((item) => item.parentId !== null).map((item) => item.parentId),
          );

          checkedKeys.value = roleMenuList
            .filter((item) => !parentIds.value.has(item.id))
            .map((item) => item.id);
        }
      });

      const getTitle = computed(() => (!unref(isUpdate) ? '新增角色' : '编辑角色'));

      function handleCheck(keys, e) {
        if (!unref(treeCheck)) {
          treeCheck.value = true;
        }
        checkedKeys.value = keys;
        halfCheckedKeys.value = e.halfCheckedKeys;
      }

      async function handleSubmit() {
        try {
          const values = await validate();
          setDrawerProps({ confirmLoading: true });
          const menuIds = unref(treeCheck)
            ? [...checkedKeys.value, ...halfCheckedKeys.value]
            : [...checkedKeys.value, ...Array.from(parentIds.value)];
          values.permissionIds = menuIds;
          if (isUpdate.value) {
            values.roleId = roleId.value;
            await updateRolePermission(values);
          } else {
            await addRole(values);
          }
          closeDrawer();
          emit('success');
        } finally {
          setDrawerProps({ confirmLoading: false });
        }
      }

      return {
        registerDrawer,
        registerForm,
        getTitle,
        handleSubmit,
        handleCheck,
        treeData,
        checkedKeys,
        selectedKeys,
      };
    },
  });
</script>
