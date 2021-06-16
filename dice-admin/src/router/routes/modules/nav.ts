import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const nav: AppRouteModule = {
  path: '/nav',
  name: 'Nav',
  component: LAYOUT,
  redirect: '/nav/nav-list',
  meta: {
    icon: 'grommet-icons:navigate',
    title: '导航',
  },
  children: [
    {
      path: 'nav-type',
      name: 'NavType',
      meta: {
        title: '导航分类',
        ignoreKeepAlive: true,
      },
      component: () => import('/@/views/nav/type/index.vue'),
    },
    {
      path: 'nav-detail',
      name: 'NavDetail',
      meta: {
        title: '导航清单',
        ignoreKeepAlive: true,
      },
      component: () => import('/@/views/nav/list/index.vue'),
    },
  ],
};

export default nav;
