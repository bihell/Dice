import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const blog: AppRouteModule = {
  path: '/blog',
  name: 'Blog',
  component: LAYOUT,
  redirect: '/blog/posts',
  meta: {
    icon: 'carbon:blog',
    title: '博客',
  },
  children: [
    {
      path: 'posts',
      name: 'PostList',
      component: () => import('/@/views/blog/post/PostList.vue'),
      meta: {
        title: '文章列表',
      },
    },
    {
      path: 'edit',
      name: 'PostEdit',
      component: () => import('/@/views/blog/post/PostEdit.vue'),
      meta: {
        title: '编辑文章',
      },
    },
    {
      path: 'comments',
      name: 'CommentList',
      component: () => import('/@/views/blog/comment/CommentList.vue'),
      meta: {
        title: '评论列表',
      },
    },
    {
      path: 'tags',
      name: 'Tags',
      component: () => import('/@/views/blog/meta/Tag.vue'),
      meta: {
        title: '标签分类',
      },
    },
    {
      path: 'pages',
      name: 'PageList',
      component: () => import('/@/views/blog/page/PageList.vue'),
      meta: {
        title: '页面列表',
      },
    },
    {
      path: 'PageEdit',
      name: 'PageEdit',
      component: () => import('/@/views/blog/page/PageEdit.vue'),
      meta: {
        title: '编辑页面',
      },
    },
    {
      path: 'BlogSetting',
      name: 'BlogSettingPage',
      component: () => import('/@/views/blog/setting/index.vue'),
      meta: {
        title: '博客设置',
      },
    },
  ],
};

export default blog;
