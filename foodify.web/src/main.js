import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import axios from './plugins/axios';
import i18n from './plugins/i18n';
import vuelidate from './plugins/vuelidate'
import vuelidateerrorextractor from './plugins/vuelidate-error-extractor'

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  axios,
  i18n,
  vuelidate,
  vuelidateerrorextractor,
  render: h => h(App)
}).$mount('#app')
