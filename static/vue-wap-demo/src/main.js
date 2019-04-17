import Vue from 'vue'
import App from './App'
import router from './router'
import './style/index.scss';
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueQuillEditor from 'vue-quill-editor'

import ElementUI from 'element-ui'

import 'element-ui/lib/theme-chalk/index.css'

import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
// import './style/bootstrap-3.3.7-dist/css/bootstrap.css'
// import './style/templatemo_main.css'

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(VueQuillEditor)
Vue.use(VueAxios,axios);

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
