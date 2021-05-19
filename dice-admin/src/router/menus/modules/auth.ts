import type { MenuModule } from '/@/router/types';

const menu: MenuModule = {
  orderNo: 1,
  menu: {
    path: '/auth',
    name: '权限',
    children: [
      {
        path: 'users',
        name: '用户列表',
      },
      {
        path: 'apis',
        name: 'API管理',
      },
      {
        path: 'role',
        name: '角色管理',
      },
    ],
  },
};
export default menu;
