import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const List = () => import('@/components/List.vue')
const Regist = () => import('@/components/Regist.vue')
const Show = () => import('@/components/Show.vue')

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: List
    },
    {
      path: '/new',
      name: 'regist',
      component: Regist
    },
    {
      path: '/:id',
      name: 'show',
      component: Show
    },
    {
      path: '/edit/:id',
      name: 'edit',
      component: Regist,
      props: true
    }
  ]
})
