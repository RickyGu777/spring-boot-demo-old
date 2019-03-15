import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/components/Layout'
import List from '@/components/List'
import Auth from '@/components/Auth'
import Father from '@/components/tool/Father'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Layout',
      component: Layout
    }, {
      path: '/List',
      name: 'List',
      component: List
    }, {
      path: '/Auth',
      name: 'Auth',
      component: Auth
    }, {
      path: '/Father',
      name: 'Father',
      component: Father
    }
  ]
})
