import { getAllRoleList } from '/@/api/sys/system';
import { BasicColumn, FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '用户名',
    dataIndex: 'username',
    width: 120,
  },
  {
    title: '昵称',
    dataIndex: 'nickname',
    width: 120,
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    width: 120,
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    width: 180,
  },
  {
    title: '备注',
    dataIndex: 'remark',
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'username',
    label: '用户名',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'nickname',
    label: '昵称',
    component: 'Input',
    colProps: { span: 8 },
  },
];

export const accountFormSchema: FormSchema[] = [
  {
    field: 'username',
    label: '用户名',
    component: 'Input',
    required: true,
  },
  {
    field: 'pwd',
    label: '密码',
    component: 'InputPassword',
    required: true,
    ifShow: false,
  },
  {
    field: 'roleIds',
    label: '角色',
    component: 'ApiSelect',
    componentProps: {
      placeholder: '请选择角色',
      api: getAllRoleList,
      mode: 'multiple',
      labelField: 'roleName',
      valueField: 'id',
    },
    required: true,
  },
  {
    field: 'deptId',
    label: '所属部门',
    component: 'TreeSelect',
    componentProps: {
      fieldNames: {
        title: 'deptName',
        key: 'id',
        value: 'id',
      },
      getPopupContainer: () => document.body,
    },
    required: true,
  },
  {
    field: 'nickname',
    label: '昵称',
    component: 'Input',
    required: true,
  },
  { field: 'phone', label: '手机', component: 'Input', required: true },
  {
    label: '邮箱',
    field: 'email',
    component: 'Input',
    required: true,
  },
  {
    label: '备注',
    field: 'remark',
    component: 'InputTextArea',
  },
];
