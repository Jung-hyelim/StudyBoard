import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    page: 1,
    BASE_URL: '/api/document',
    rules: {
      required: v => !!v || '필수입력값입니다.',
      largerThanZero: v => !(v < 0) || '음수는 입력될 수 없습니다.'
    }
  },
  mutations: {
    changePage (state, payload) {
      state.page = payload
    }
  }
})
