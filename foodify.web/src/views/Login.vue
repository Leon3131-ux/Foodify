<template>
  <v-main>
    <v-container>
      <v-row>
        <v-col cols="12">
      <v-card class="px-4 py-4" >
      <h1>{{ $t("Login") }}</h1>
      <form-wrapper :validator="$v">
        <form-group name="username">
          <v-text-field
              color="primary"
            v-model="username"
            label="Username"
            slot-scope="{ attrs }"
            v-bind="attrs"
          /><br />
        </form-group>

        <form-group name="password">
          <v-text-field
              color="primary"
            v-model="password"
            type="password"
            label="Password"
            slot-scope="{ attrs }"
            v-bind="attrs"
          />
        </form-group>
      </form-wrapper>
      <v-btn @click="submitLogin">login</v-btn>
      <v-btn type="a" to="Register">Register</v-btn>
      <v-alert
        :value="alert"
        color="negative"
        border="top"
        icon="mdi-home"
        transition="scale-transition"
      >
        Phasellus tempus. Fusce ac felis sit amet ligula pharetra condimentum.
        In dui magna, posuere eget, vestibulum et, tempor auctor, justo.
        Pellentesque posuere. Curabitur ligula sapien, tincidunt non, euismod
        vitae, posuere imperdiet, leo. Phasellus nec sem in justo pellentesque
        facilisis. Phasellus magna. Cras risus ipsum, faucibus ut, ullamcorper
        id, varius ac, leo. In hac habitasse platea dictumst. Praesent turpis.
      </v-alert>
      </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>


<script>
import { required } from "vuelidate/lib/validators";
export default {
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
    return {
      password: "",
      username: "",
      alert: false,
    };
  },
  methods: {
    async submitLogin() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }
      let res = await axios.post("/login", {
        username: this.username,
        password: this.password,
      });
      if (res.status === 200) {
        const token = res.data;
        if (token == undefined) {
          this.alert = true;
          this.password = "";
          this.$v.$reset();
          return;
        }
        this.$store.dispatch("logout");
        this.$store.dispatch("login", {
          jwt: token,
        });
        this.$router.push("/Dashboard");
      } else {
        this.alert = true;
        this.password = "";
        this.$v.$reset();
        return;
      }
    },
  },
};
</script>
