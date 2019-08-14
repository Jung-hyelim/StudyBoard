import Vue from 'vue'
import App from './App'
import router from './router'
import vuetify from './plugins/vuetify'
import '@babel/polyfill'
import {store} from './store'

Vue.config.productionTip = false

/* eslint-disable */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  vuetify,
  template: '<App/>'
})
