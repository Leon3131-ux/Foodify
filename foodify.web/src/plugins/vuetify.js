import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);
export default new Vuetify({

        theme: {
            themes: {
                dark: {
                    primary: '#349D1C',
                    secondary: '#b2fda1',
                    accent: '#8c9eff',
                    error: '#b71c1c',
                },
            },
        },
});
