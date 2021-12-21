import { BasicColumn } from '/@/components/Table';
import { FormSchema } from '/@/components/Table';
import { uploadApi } from '/@/api/sys/upload';
import { deleteFile } from '/@/api/sys/deleteFile';

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
    slots: { customRender: 'avatar' },
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    width: 170,
  },
  {
    title: '排序',
    dataIndex: 'sort',
    width: 100,
  },
  {
    title: '地址',
    dataIndex: 'url',
    width: 300,
    slots: { customRender: 'link' },
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
      fieldNames: {
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
    component: 'Upload',
    label: '图标',
    colProps: {
      span: 8,
    },
    rules: [{ required: true, message: '请选择上传文件' }],
    componentProps: {
      api: uploadApi,
      multiple: false,
      maxNumber: 1,
      maxSize: 1,
      uploadParams: { type: 'nav' },
      onDelete: (data) => {
        const params = { file: data.responseData.result, type: 'nav' };
        deleteFile(params);
      },
      onPreviewDelete: (url) => {
        const params = { file: url, type: 'nav' };
        deleteFile(params);
      },
    },
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
