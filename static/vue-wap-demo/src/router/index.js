import Vue from 'vue'
import Router from 'vue-router'
import UploadJoke from '@/components/UploadJoke'
import List from '@/components/List'
import Auth from '@/components/Auth'
import Diary from '@/components/Diary'
import HomePage from '@/components/HomePage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/UploadJoke',
      name: 'UploadJoke',
      component: UploadJoke
    }, {
      path: '/JokeList',
      name: 'JokeList',
      component: List
    }, {
      path: '/Auth',
      name: 'Auth',
      component: Auth
    }, {
      path: '/Diary',
      name: 'Diary',
      component: Diary
    }
  ]
})
