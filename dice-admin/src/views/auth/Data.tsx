import { BasicColumn, FormProps } from '/@/components/Table';
import { formatToDateTime } from '/@/utils/dateUtil';

export function getUserColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: '用户名',
      width: 200,
      dataIndex: 'username',
      align: 'left',
    },
    {
      title: '别名',
      dataIndex: 'screenName',
      width: 200,
      align: 'left',
    },
    {
      title: '邮箱',
      dataIndex: 'email',
      // width: 80,
      align: 'left',
    },
    {
      title: '创建时间',
      sorter: true,
      dataIndex: 'created',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.created),
    },
    {
      title: '最后登陆时间',
      sorter: true,
      dataIndex: 'logged',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.logged),
    },
  ];
}

export function getApiColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: 'Api地址',
      dataIndex: 'apiPath',
      align: 'left',
    },
    {
      title: '所属项目',
      dataIndex: 'projectType',
      width: 200,
      align: 'left',
    },
    {
      title: '创建日期',
      sorter: true,
      dataIndex: 'createTime',
      width: 160,
      customRender: ({ record }) => formatToDateTime(record.createTime),
    },
    {
      title: '更新日期',
      sorter: true,
      dataIndex: 'updateTime',
      width: 160,
      customRender: ({ record }) => formatToDateTime(record.updateTime),
    },
  ];
}

export function getRoleColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: '角色名称',
      dataIndex: 'roleName',
      align: 'left',
    },
    {
      title: '所属项目',
      dataIndex: 'projectType',
      // width: 200,
      align: 'left',
    },
    {
      title: '有效用户数',
      dataIndex: 'userCount',
      width: 200,
      align: 'left',
    },
    {
      title: '创建日期',
      sorter: true,
      dataIndex: 'createTime',
      width: 160,
      customRender: ({ record }) => formatToDateTime(record.createTime),
    },
    {
      title: '更新日期',
      sorter: true,
      dataIndex: 'updateTime',
      width: 160,
      customRender: ({ record }) => formatToDateTime(record.updateTime),
    },
  ];
}

export function getApiFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 10,
    schemas: [
      {
        field: `status`,
        label: ` `,
        component: 'Select',
        slot: 'apiAdd',
        colProps: {
          xl: 4,
          xxl: 2,
        },
      },
      {
        field: `projectType`,
        label: ` `,
        component: 'Select',
        componentProps: {
          placeholder: '所属项目',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
      {
        field: `criteria`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: 'api',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
    ],
  };
}

export function getRoleFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 10,
    schemas: [
      {
        field: `status`,
        label: ` `,
        component: 'Select',
        slot: 'add',
        colProps: {
          xl: 4,
          xxl: 2,
        },
      },
      {
        field: `projectType`,
        label: ` `,
        component: 'Select',
        componentProps: {
          placeholder: '所属项目',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
      {
        field: `criteria`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: '角色名称',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
    ],
  };
}

export function getUserFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 10,
    schemas: [
      {
        field: `status`,
        label: ` `,
        component: 'Select',
        slot: 'userAdd',
        colProps: {
          xl: 4,
          xxl: 2,
        },
      },
      {
        field: `criteria`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: '请输入用户名',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
    ],
  };
}
