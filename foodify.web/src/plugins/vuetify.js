import '@fortawesome/fontawesome-free/css/all.css' 
import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);
export default new Vuetify({
    icons: {
        iconfont: 'fa',
    },
    theme: {
        themes: {
            dark: {
                primary: '#349D1C',
                secondary: '#4caf50',
                accent: '#8c9eff',
                error: '#b71c1c',
            },
        },
    },
});
