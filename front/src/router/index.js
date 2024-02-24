import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
      path: '/',
      name: 'main',
      component: () => import('../views/SubwayLineInfo.vue')
    },
    {
      path: '/emergencyPage/:subNM',
      name: 'emergencyPage',
      props: true,
       component: () => import('../views/emergencyPage.vue') 
     },
    {
     path: '/subwayChart/:subNM',
     name: 'subwayChart',
     props: true,
      component: () => import('../views/subwayChart.vue') 
    },
/*     {
      path: '/paging',
      name: 'paging',
      props: true,
       component: () => import('../components/paging.vue') 
     } */
    
  ]
})

export default router
