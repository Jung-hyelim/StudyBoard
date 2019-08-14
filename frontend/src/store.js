import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    page: 1
  },
  mutations: {
    changePage (state, payload) {
      state.page = payload
    }
  }
})
