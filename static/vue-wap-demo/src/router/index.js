import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/HomePage'
import UploadJoke from '@/components/Joke/UploadJoke'
import JokeList from '@/components/Joke/JokeList'
import JokeDetail from '@/components/Joke/JokeDetail'
import Auth from '@/components/Auth/Auth'
import Diary from '@/components/Diary/Diary'
import DiaryDetail from '@/components/Diary/DiaryDetail'
import Word from '@/components/Baidu/Word'

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
      component: JokeList
    }, {
      path: '/Auth',
      name: 'Auth',
      component: Auth
    }, {
      path: '/Diary',
      name: 'Diary',
      component: Diary
    }, {
      path: '/DiaryDetail',
      name: 'DiaryDetail',
      component: DiaryDetail
    }, {
      path: '/JokeDetail',
      name: 'JokeDetail',
      component: JokeDetail
    }, {
      path: '/Word',
      name: 'Word',
      component: Word
    }
  ]
});
