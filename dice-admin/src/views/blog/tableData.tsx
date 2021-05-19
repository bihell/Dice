import { FormProps, FormSchema } from '/@/components/Table';
import { BasicColumn } from '/@/components/Table/src/types/table';
import { formatToDateTime } from '/@/utils/dateUtil';

export function getBasicColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: '标题',
      dataIndex: 'title',
      align: 'left',
    },
    {
      title: '分类',
      dataIndex: 'category',
      width: 80,
      align: 'center',
      slots: { customRender: 'category' },
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: 70,
      align: 'center',
      slots: { customRender: 'status' },
    },
    {
      title: '评论',
      dataIndex: 'commentCount',
      width: 60,
      align: 'center',
      slots: { customRender: 'cc' },
    },
    {
      title: '发布日期',
      sorter: true,
      dataIndex: 'createTime',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.createTime),
    },
    {
      title: '修改日期',
      sorter: true,
      dataIndex: 'updateTime',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.updateTime),
    },
  ];
}

export function getPageColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: '标题',
      dataIndex: 'title',
      align: 'left',
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: 70,
      align: 'center',
      slots: { customRender: 'status' },
    },
    {
      title: '评论',
      dataIndex: 'commentCount',
      width: 60,
      align: 'center',
      slots: { customRender: 'cc' },
    },
    {
      title: '发布日期',
      sorter: true,
      dataIndex: 'createTime',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.createTime),
    },
    {
      title: '修改日期',
      sorter: true,
      dataIndex: 'updateTime',
      width: 150,
      customRender: ({ record }) => formatToDateTime(record.updateTime),
    },
  ];
}

export function getCommentColumns(): BasicColumn[] {
  return [
    {
      title: 'ID',
      width: 60,
      dataIndex: 'id',
    },
    {
      title: '称呼',
      width: 120,
      dataIndex: 'name',
      align: 'left',
    },
    {
      title: '内容',
      dataIndex: 'content',
      align: 'left',
    },
    {
      title: '邮箱',
      dataIndex: 'email',
      width: 200,
      align: 'left',
    },
    {
      title: '评论日期',
      dataIndex: 'created',
      width: 150,
      align: 'center',
      customRender: ({ record }) => formatToDateTime(record.created),
    },
  ];
}

export function getFormConfig(): Partial<FormProps> {
  return {
    labelWidth: 10,
    schemas: [
      {
        field: `status`,
        label: ` `,
        component: 'Select',
        // slot: 'advanceBefore',
        componentProps: {
          placeholder: '状态',
          options: [
            {
              label: '不限制',
              value: null,
            },
            {
              label: '已发布',
              value: 'PUBLISHED',
            },
            {
              label: '草稿',
              value: 'DRAFT',
            },
            {
              label: '回收站',
              value: 'RECYCLE',
            },
            {
              label: '私密',
              value: 'INTIMATE',
            },
          ],
        },
        colProps: {
          xl: 4,
          xxl: 2,
        },
      },
      {
        field: `priority`,
        label: ` `,
        component: 'Select',
        componentProps: {
          placeholder: '是否置顶',
          options: [
            {
              label: '不限制',
              value: null,
            },
            {
              label: '普通',
              value: 0,
            },
            {
              label: '置顶',
              value: 1,
            },
          ],
        },
        colProps: {
          xl: 4,
          xxl: 2,
        },
      },
      {
        field: `title`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: '搜索标题',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
      {
        field: `content`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: '搜索内容',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
    ],
  };
}

export function getPageConfig(): Partial<FormProps> {
  return {
    labelWidth: 10,
    schemas: [
      {
        field: `status`,
        label: ` `,
        component: 'Select',
        componentProps: {
          placeholder: '状态',
          options: [
            {
              label: '不限制',
              value: null,
            },
            {
              label: '已发布',
              value: 'PUBLISHED',
            },
            {
              label: '草稿',
              value: 'DRAFT',
            },
            {
              label: '回收站',
              value: 'RECYCLE',
            },
            {
              label: '私密',
              value: 'INTIMATE',
            },
          ],
        },
        colProps: {
          xl: 4,
          xxl: 2,
        },
      },
      {
        field: `title`,
        label: ` `,
        component: 'Input',
        componentProps: {
          placeholder: '搜索功能待实现',
        },
        colProps: {
          xl: 14,
          xxl: 7,
        },
      },
    ],
  };
}

export const blogSetSchemas: FormSchema[] = [
  {
    field: 'blog_name',
    component: 'Input',
    label: '博客名：',
    colProps: { span: 18 },
  },
  {
    field: 'blog_website',
    component: 'Input',
    label: '博客地址：',
    colProps: { span: 18 },
  },
  {
    field: 'blog_footer',
    component: 'InputTextArea',
    label: '博客底部信息：',
    colProps: { span: 18 },
  },
];

export const seoSetSchemas: FormSchema[] = [
  {
    field: 'meta_title',
    component: 'Input',
    label: '网站名称（Title）：',
    colProps: { span: 18 },
  },
  {
    field: 'meta_description',
    component: 'Input',
    label: '网站描述（description）：',
    colProps: { span: 18 },
  },
  {
    field: 'meta_keywords',
    component: 'Input',
    label: '网站关键字（keywords）：',
    colProps: { span: 18 },
  },
  {
    field: 'google_site_verification',
    component: 'Input',
    label: 'Google站点验证（google-site-verification）：',
    colProps: { span: 18 },
  },
  {
    field: 'baidu_site_verification',
    component: 'Input',
    label: 'Baidu站点验证（baidu-site-verification）：',
    colProps: { span: 18 },
  },
  {
    field: 'google_analytics',
    component: 'Input',
    label: 'Google站点分析（google_analytics）：',
    colProps: { span: 18 },
  },
];

export const mailSetSchemas: FormSchema[] = [
  {
    field: 'is_email',
    component: 'Switch',
    label: '设置邮箱提醒：',
    colProps: { span: 18 },
  },
  {
    field: 'email_username',
    component: 'Input',
    label: '邮箱：',
    colProps: { span: 18 },
  },
  {
    field: 'email_password',
    component: 'InputPassword',
    label: '邮箱密码：',
    colProps: { span: 18 },
  },
  {
    field: 'email_host',
    component: 'Input',
    label: '主机名：',
    colProps: { span: 18 },
  },
  {
    field: 'email_port',
    component: 'Input',
    label: '端口号：',
    colProps: { span: 18 },
  },
  {
    field: 'email_subject',
    component: 'Input',
    label: '邮件标题：',
    colProps: { span: 18 },
  },
];

// todo tab的list
export const settingList = [
  {
    key: '1',
    name: '博客设置',
    register: 'blogSetRegister',
  },
  {
    key: '2',
    name: 'SEO 设置',
    register: 'seoSetRegister',
  },
  {
    key: '3',
    name: '邮箱设置',
    register: 'mailSetRegister',
  },
];
