<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
        text
      >
        <span class="mr-2">Latest Release</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      {{ test }}
      {{ $t("failed") }}
      <form-wrapper :validator="$v">
        <form-group name="username">
          <v-text-field
            v-model="username"
            label="Username"
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
      </form-wrapper>
      <v-btn @click="submitLogin">login</v-btn>
    </v-main>
  </v-app>
</template>

<script>
import { required } from "vuelidate/lib/validators";
export default {
  name: "App",
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
    this.$store.dispatch("auth/login", {
      jwt: "token",
    });
    let res = await axios.get("test", {
      params: {
        id: 1,
      },
    });
    if (res.status === 200) {
      console.log(res.data);
    }
  },
  data() {
    return {
      test: "asd",
      password: "",
      username: "",
    };
  },
  methods: {
    async submitLogin() {
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }
      let res = await axios.post("/User/Login", {
        Username: this.username,
        Password: this.password,
      });
      if (res.status === 200) {
        const token = res.data.token;
        if (token == undefined) {
          Notify.create({
            position: "top",
            type: "negative",
            message: "login failed",
          });
          this.password = "";
          this.$v.$reset();
          return;
        }
        this.$store.dispatch("auth/logout");
        this.$store.dispatch("auth/login", {
          jwt: token,
        });
        Notify.create({
          position: "top",
          type: "positive",
          message: "login sucssesd",
        });
        this.$router.push("/ReferenzenEdit");
      }
    },
  },
};
</script>
