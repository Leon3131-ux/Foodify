import VueI18n from 'vue-i18n'
import messages from '../i18n/index'
import store from './../store'
import Vue from 'vue';


var local = store.getters["global/locale"]
if (!local) {
  local = 'de'
}

Vue.use(VueI18n)
export default new VueI18n({
  locale: local,
  fallbackLocale: 'de',
  messages
})