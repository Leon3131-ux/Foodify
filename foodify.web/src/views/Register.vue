<template>
  <v-main>
    <v-container>
      <h1>{{ $t("Register") }}</h1>
      <form-wrapper :validator="$v">
        <form-group name="username">
          <v-text-field
            v-model="username"
            label="Username"
            slot-scope="{ attrs }"
            v-bind="attrs"
          /><br />
        </form-group>

        <form-group name="email">
          <v-text-field
            v-model="email"
            label="email"
            slot-scope="{ attrs }"
            v-bind="attrs"
          /><br />
        </form-group>

        <form-group name="password">
          <v-text-field
            v-model="password"
            type="password"
            label="Password"
            slot-scope="{ attrs }"
            v-bind="attrs"
          />
        </form-group>

        <form-group name="repeatPassword">
          <v-text-field
            v-model="repeatPassword"
            type="password"
            label="repeatPassword"
            slot-scope="{ attrs }"
            v-bind="attrs"
          />
        </form-group>
      </form-wrapper>
      <v-btn @click="register">Registrieren</v-btn>
    </v-container>
  </v-main>
</template>


<script>
import { required, sameAs, email } from "vuelidate/lib/validators";
export default {
  validations: {
    password: {
      required,
    },
    repeatPassword: {
      required,
      sameAsPassword: sameAs("password"),
    },
    username: {
      required,
      isUnique(value) {
        return this.getisusedusername;
      },
    },
    email: {
      required,
      email,
    },
  },
  async mounted() {},
  computed: {
    getisusedusername() {
      return this.isusedusername;
    },
  },
  data() {
    return {
      password: "",
      repeatPassword: "",
      username: "",
      email: "",
      isusedusername: true,
    };
  },
  methods: {
    async register() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }
      let res = await axios.post("/register", {
        username: this.username,
        password: this.password,
        repeatPassword: this.repeatPassword,
        email: this.email,
      });
      if (res.status == 200) {
        this.$router.push("/Login");
      } else {
        this.isusedusername = false;
      }
    },
  },
};
</script>
