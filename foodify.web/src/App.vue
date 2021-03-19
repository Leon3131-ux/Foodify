<template>
  <v-app>
    <router-view />
  </v-app>
</template>
<script>
import {required} from "vuelidate/lib/validators";

export default {

  async mounted() {
    this.$vuetify.theme.dark = true;
  },
  data() {
  },
  methods: {
  },
};
</script>

