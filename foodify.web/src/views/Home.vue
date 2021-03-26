<template>
  <v-main> </v-main>
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
  async mounted() {},
  data() {
    return {};
  },
  methods: {},
};
</script>
