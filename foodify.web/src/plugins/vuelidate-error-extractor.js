import VuelidateErrorExtractor, { templates } from "vuelidate-error-extractor";
import FormGroup from "../components/shared/FormGroup.vue";
import Vue from 'vue';
// import FormSummary from "../components/FormSummary.vue";

const validationKeys = {
    // minLength: { // Validation rule name in vuelidate
    //   validationKey: 'min.string', // Validation key inside our deep messages object, Laravel shown here.
    //   params: [
    //     {
    //       vue: 'min', // Vuelidate param name
    //       ext: 'min' // Messages param name
    //     }
    //   ]
    // },
    // sameAs: {
    //   validationKey: 'same',
    //   params: [
    //     {
    //       vue: 'eq', // Vuelidate uses eq for other value 
    //       ext: 'other' // Laravel uses other
    //     }
    //   ]
    // }
}

Vue.component("FormGroup", FormGroup);
Vue.component("formWrapper", templates.FormWrapper);
Vue.use(VuelidateErrorExtractor, {
    i18n: 'validation',
    validationKeys
});
export default () => {


}