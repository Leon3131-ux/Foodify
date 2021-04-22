import axios from 'axios'
import store from './../store'
import Vue from 'vue';

const instance = axios.create({
  baseURL : process.env.VUE_APP_API_URL,
  headers:{
    'Authorization': 'Bearer ' +  store.getters["JWT"]
  }
})

instance.interceptors.request.use(request => {
  console.log(store.getters["JWT"])
  if(request.headers.Authorization == "Bearer null"){
    delete request.headers["Authorization"];
  }

  return request
})

Vue.prototype.$axios = instance
window.axios = instance

export default instance