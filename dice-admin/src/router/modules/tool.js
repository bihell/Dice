/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const toolRouter = {
  path: '/tool',
  component: Layout,
  redirect: '/tool/snippet',
  name: 'tool',
  meta: {
    title: '工具',
    icon: 'Tool'
  },
  children: [
    {
      path: '/snippet',
      component: () => import('@/views/tool/snippet/Repository.vue'),
      name: 'snippet',
      meta: { title: '代码段' }
    },
    {
      path: '/media-library',
      component: () => import('@/views/tool/media-library/Index.vue'),
      name: 'media-library',
      meta: { title: '媒体库' }
    }
  ]
}
export default toolRouter
