import axios from 'axios'
import store from './../store'
import Vue from 'vue';

const instance = axios.create({
  baseURL : process.env.VUE_APP_API_URL,
  headers:{
    'authorization': 'Bearer ' +  store.getters["auth/JWT"]
  }
})
Vue.prototype.$axios = instance
window.axios = instance
Vue.use(instance);

export default instance