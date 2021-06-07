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
      component: () => import('/@/views/nav/type/TypeList.vue'),
      meta: {
        title: '导航分类',
      },
    },
    {
      path: 'nav-detail',
      name: 'NavDetail',
      component: () => import('/@/views/nav/detail/DetailList.vue'),
      meta: {
        title: '导航列表',
      },
    },
  ],
};

export default nav;
