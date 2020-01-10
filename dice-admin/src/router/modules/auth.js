/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const authRouter = {
  path: '/auth',
  component: Layout,
  redirect: '/auth/user/list',
  name: 'auth',
  meta: {
    title: '权限',
    icon: 'Blog'
  },
  children: [
    {
      path: 'user/list',
      component: () => import('@/views/auth/user'),
      name: 'UserList',
      meta: { title: '用户列表', icon: 'documentation' }
    },
    {
      path: 'menu/list',
      component: () => import('@/views/auth/menu'),
      name: 'MenuList',
      meta: { title: '菜单管理', icon: 'documentation' }
    },
    {
      path: 'api/list',
      component: () => import('@/views/auth/api'),
      name: 'ApiList',
      meta: { title: 'API管理', icon: 'documentation' }
    },
    {
      path: 'role/list',
      component: () => import('@/views/auth/role'),
      name: 'RoleList',
      meta: { title: '角色管理', icon: 'documentation' }
    },
    {
      path: 'role/item',
      component: () => import('@/views/auth/menu'),
      name: 'RoleMenuItem',
      meta: { title: '分配权限', icon: 'documentation' },
      hidden: true
    },
    {
      path: 'role/show',
      component: () => import('@/views/auth/menu'),
      name: 'RoleMenuShow',
      meta: { title: '查看权限', icon: 'documentation' },
      hidden: true
    },
    {
      path: 'content/list',
      component: () => import('@/views/auth/content'),
      name: 'ContentList',
      meta: { title: '内容管理', icon: 'documentation' }
    }
  ]
}
export default authRouter

