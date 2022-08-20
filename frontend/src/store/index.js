import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  getters: {
    getWorker(state) {
      return state.worker
    }
  },
  mutations: {
    setWorker(state, worker) {
      state.worker = worker
    }
  },
  state: {
    worker: {}
  }
});
