import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';

export const columns: BasicColumn[] = [
  {
    title: '名称',
    dataIndex: 'name',
    width: 100,
  },
  {
    title: '图标',
    dataIndex: 'icon',
    width: 100,
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    width: 180,
  },
  {
    title: '地址',
    dataIndex: 'url',
    width: 200,
  },
  {
    title: '描述',
    dataIndex: 'description',
  },
];

export const searchFormSchema: FormSchema[] = [
  {
    field: 'name',
    label: '导航名',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'description',
    label: '描述',
    component: 'Input',
    colProps: { span: 8 },
  },
  {
    field: 'url',
    label: '地址',
    component: 'Input',
    colProps: { span: 8 },
  },
];

export const navDetailFormSchema: FormSchema[] = [
  {
    field: 'name',
    label: '名称',
    component: 'Input',
    required: true,
  },
  {
    field: 'typeId',
    label: '所属分类',
    component: 'TreeSelect',
    componentProps: {
      replaceFields: {
        title: 'name',
        key: 'id',
        value: 'id',
      },
      getPopupContainer: () => document.body,
    },
    required: true,
  },
  {
    field: 'url',
    label: '地址',
    component: 'Input',
    required: true,
  },
  {
    field: 'icon',
    label: '图标',
    component: 'Input',
    required: true,
  },
  {
    label: '排序',
    field: 'sort',
    component: 'Input',
  },
  {
    label: '描述',
    field: 'description',
    component: 'InputTextArea',
  },
];
