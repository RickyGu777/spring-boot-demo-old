import Vue from 'vue'
import Router from 'vue-router'
import HelloFromVux from '@/components/HelloFromVux'
import XButtonDemo from '@/components/test/XButtonDemo'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloFromVux',
      component: HelloFromVux
    },
    {
      path: '/XButtonDemo',
      name: 'XButtonDemo',
      component: XButtonDemo
    }
  ]
})
