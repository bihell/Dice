import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const system: AppRouteModule = {
  path: '/system',
  name: 'System',
  component: LAYOUT,
  redirect: '/system/account',
  meta: {
    orderNo: 2000,
    icon: 'ion:settings-outline',
    title: '系统',
  },
  children: [
    {
      path: 'account',
      name: 'AccountManagement',
      meta: {
        title: '用户管理',
        ignoreKeepAlive: false,
      },
      component: () => import('/@/views/sys/account/index.vue'),
    },
    // {
    //   path: 'account_detail/:id',
    //   name: 'AccountDetail',
    //   meta: {
    //     hideMenu: true,
    //     title: t('routes.demo.system.account_detail'),
    //     ignoreKeepAlive: true,
    //     showMenu: false,
    //     currentActiveMenu: '/system/account',
    //   },
    //   component: () => import('/@/views/demo/system/account/AccountDetail.vue'),
    // },
    {
      path: 'role',
      name: 'RoleManagement',
      meta: {
        title: '角色管理',
        ignoreKeepAlive: true,
      },
      component: () => import('/@/views/sys/role/index.vue'),
    },
    {
      path: 'menu',
      name: 'MenuManagement',
      meta: {
        title: '菜单管理',
        ignoreKeepAlive: true,
      },
      component: () => import('/@/views/sys/menu/index.vue'),
    },
    {
      path: 'dept',
      name: 'DeptManagement',
      meta: {
        title: '部门管理',
        ignoreKeepAlive: true,
      },
      component: () => import('/@/views/sys/dept/index.vue'),
    },
    // {
    //   path: 'changePassword',
    //   name: 'ChangePassword',
    //   meta: {
    //     title: t('routes.demo.system.password'),
    //     ignoreKeepAlive: true,
    //   },
    //   component: () => import('/@/views/demo/system/password/index.vue'),
    // },
  ],
};

export default system;
