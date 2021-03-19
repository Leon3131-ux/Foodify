<template>
  <v-main>

    <v-btn @click="submitLogin">login</v-btn>
    <v-btn @click="submitLogin">Anmelden</v-btn>
  </v-main>
</template>


<script>
import { required } from "vuelidate/lib/validators";
export default {
  name: "Home",
  validations: {
    password: {
      required,
    },
    username: {
      required,
    },
  },
  components: {},
  async mounted() {
  },
  data() {
  },
  methods: {
  },
};
</script>
