/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const blogRouter = {
  path: '/blog',
  component: Layout,
  redirect: '/blog/article',
  name: 'blog',
  meta: {
    title: '博客',
    icon: 'Blog'
  },
  children: [
    {
      path: 'article',
      component: () => import('@/views/blog/Articles'),
      name: 'ArticleList',
      meta: { title: '文章列表', icon: 'documentation' }
    },
    {
      path: 'article/edit/:id(\\d+)',
      component: () => import('@/views/blog/Article'),
      name: 'EditArticle',
      meta: { title: '编辑文章', activeMenu: '/blog/article' },
      hidden: true
    },
    {
      path: 'article/create',
      component: () => import('@/views/blog/Article'),
      name: 'CreateArticle',
      meta: { title: '创建文章', activeMenu: '/blog/article' },
      hidden: true
    },
    {
      path: 'comment',
      component: () => import('@/views/blog/Comments'),
      name: 'Comment',
      meta: { title: '评论列表', icon: 'chat' }
    },
    {
      path: 'tag',
      component: () => import('@/views/blog/Tags'),
      name: 'Tag',
      meta: { title: '标签分类', icon: 'Tag' }
    },
    {
      path: 'page',
      component: () => import('@/views/blog/Pages'),
      name: 'Page',
      meta: { title: '页面列表', icon: 'page' }
    },
    {
      path: 'page/edit/:id(\\d+)',
      component: () => import('@/views/blog/Page'),
      name: 'EditPage',
      meta: { title: '编辑页面', activeMenu: '/blog/page' },
      hidden: true
    },
    {
      path: 'page/create',
      component: () => import('@/views/blog/Page'),
      name: 'CreatePage',
      meta: { title: '创建页面', activeMenu: '/blog/page' },
      hidden: true
    },
    {
      path: 'index',
      component: () => import('@/views/blog/Setting'),
      name: 'Setting',
      meta: { title: '网站设置', icon: 'setting' }
    }
  ]
}
export default blogRouter

