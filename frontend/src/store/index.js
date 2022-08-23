import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist';

Vue.use(Vuex)

const vuexSession = new VuexPersistence({
  storage: window.sessionStorage,
  saveState: (key, state, storage) => {
    const stateStringData = JSON.stringify(state);
    storage.setItem(key, stateStringData);
  },
});

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
  },
  plugins: [vuexSession.plugin]
});
