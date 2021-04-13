<template>
  <v-main>
    <v-container>
      <v-card color="white" class="black--text mb-3">
        <v-card-title class="primary white--text"
          >What is Foodify?</v-card-title
        >
        <v-row>
          <v-col
              cols="6"
          >
            <v-card-text
                class="black--text"
                width="50%"
              >Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
            </v-card-text>
          </v-col>
          <v-col
              cols="6">
            <v-img
                class="mt-3 mr-3"
                col
                float="right"
                src="https://www.kuechen-staude.de/wp-content/uploads/2020/02/magazin-startseite-2560x1440-kuehlschrank-a-z.jpg"
            ></v-img>
          </v-col>
        </v-row>
      </v-card>

      <v-spacer></v-spacer>
      <v-card color="white" class="black--text mb-3 mt-8">
        <v-card-title class="primary white--text">Why foodify?</v-card-title>
        <v-row>
        <v-col
            cols="6">
          <v-img
              class="mt-3 mr-3 ml-4"
              col
              float="left"
              src="https://www.kuechen-staude.de/wp-content/uploads/2020/02/magazin-startseite-2560x1440-kuehlschrank-a-z.jpg"
          ></v-img>
        </v-col>
        <v-col
            cols="6"
        >
          <v-card-text
              class="black--text"
              width="50%"
          >Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
          </v-card-text>
        </v-col>
        </v-row>
      </v-card>
      <v-row>
        <v-col cols="12">
          <v-btn color="primary"
                 width="100%"
                 height="80px"
                 class="ml-25 mt-3 mr-10"
                 v-on:click="loginpage"
          >
            sign in now
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </v-main>
</template>
<script>
import Login from "@/views/Login";

export default {
  methods: {
    loginpage() {
      this.$router.push({name: "Login"});
    }
  }
}

</script>
