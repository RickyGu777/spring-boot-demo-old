import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/Layout'
import List from '@/components/List'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Layout',
      component: Layout
    },{
      path: '/List',
      name: 'List',
      component: List
    }
  ]
})
