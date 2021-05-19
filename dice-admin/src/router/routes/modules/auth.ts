import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const auth: AppRouteModule = {
  path: '/auth',
  name: 'Auth',
  component: LAYOUT,
  redirect: '/auth/users',
  meta: {
    icon: 'cib:auth0',
    title: '权限',
  },
  children: [
    {
      path: 'users',
      name: 'UserList',
      component: () => import('/@/views/auth/user/UserList.vue'),
      meta: {
        title: '用户列表',
      },
    },
    {
      path: 'apis',
      name: 'ApiList',
      component: () => import('/@/views/auth/api/ApiList.vue'),
      meta: {
        title: 'API管理',
      },
    },
    {
      path: 'role',
      name: 'RoleList',
      component: () => import('/@/views/auth/role/RoleList.vue'),
      meta: {
        title: '角色管理',
      },
    },
  ],
};

export default auth;
