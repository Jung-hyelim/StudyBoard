import Vue from 'vue'
import App from './App'
import router from './router'
import vuetify from './plugins/vuetify'
import '@babel/polyfill'

Vue.config.productionTip = false

/* eslint-disable */
new Vue({
  el: '#app',
  router,
  components: { App },
  vuetify,
  template: '<App/>'
})
