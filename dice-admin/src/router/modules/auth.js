/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const authRouter = {
  path: '/auth',
  component: Layout,
  redirect: '/auth/user/list',
  name: 'auth',
  meta: {
    title: '权限',
    icon: 'user'
  },
  children: [
    {
      path: 'user/list',
      component: () => import('@/views/auth/user'),
      name: 'UserList',
      meta: { title: '用户列表' }
    },
    {
      path: 'menu/list',
      component: () => import('@/views/auth/menu'),
      name: 'MenuList',
      meta: { title: '菜单管理' }
    },
    {
      path: 'api/list',
      component: () => import('@/views/auth/api'),
      name: 'ApiList',
      meta: { title: 'API管理' }
    },
    {
      path: 'role/list',
      component: () => import('@/views/auth/role'),
      name: 'RoleList',
      meta: { title: '角色管理' }
    },
    {
      path: 'role/item',
      component: () => import('@/views/auth/menu'),
      name: 'RoleMenuItem',
      meta: { title: '分配权限' },
      hidden: true
    },
    {
      path: 'role/show',
      component: () => import('@/views/auth/menu'),
      name: 'RoleMenuShow',
      meta: { title: '查看权限' },
      hidden: true
    },
    {
      path: 'content/list',
      component: () => import('@/views/auth/content'),
      name: 'ContentList',
      meta: { title: '内容管理' }
    }
  ]
}
export default authRouter

