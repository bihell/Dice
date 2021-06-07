import type { MenuModule } from '/@/router/types';

const menu: MenuModule = {
  orderNo: 1,
  menu: {
    path: '/nav',
    name: '导航',
    children: [
      {
        path: 'nav-type',
        name: '导航分类',
      },
      {
        path: 'nav-detail',
        name: '导航列表',
      },
    ],
  },
};
export default menu;
