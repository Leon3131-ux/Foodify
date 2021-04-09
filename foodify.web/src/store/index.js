import Vue from 'vue'
import Vuex from 'vuex'

import modulauth from './auth'
import modulglobal from './global'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex)

const vuexLocal = new VuexPersistence({
  storage: window.sessionStorage
})


const store = new Vuex.Store({
  plugins: [vuexLocal.plugin],
  modules: {
    global: modulglobal,
    auth:modulauth
  }
})

export default store
