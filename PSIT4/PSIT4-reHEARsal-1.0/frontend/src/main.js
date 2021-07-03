import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/store.js'
import { ApiClient } from './api/server/index'

/* CSS imports */
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'
//import './css/custom.scss'

export const serverBus = new Vue()

Vue.config.productionTip = false


export const vueInstance = new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
