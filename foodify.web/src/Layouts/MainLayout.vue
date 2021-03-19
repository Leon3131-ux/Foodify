<template>
  <v-layout view="lHh Lpr lFf">
    <v-app-bar app color="primary" dark>
      <v-toolbar-title>Desktop Menu</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
            v-for="item in nav"
            color="secondary"
            :key="item.icon"
            :to="item.to"
            :title="item.title"
            flat
        >{{ item.text }}</v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <router-view />
  </v-layout>
</template>

<script>
export default {
  name: "MainLayout",
  components: {},
  data() {
    return {
      nav: [
        {
          title: "login",
          icon: "",
          text: "login",
          to: "login",
        },
        {
          title: "anmelden",
          icon: "",
          text: "anmelden",
          to: "anmelden",
        },
        {
          title: "About",
          icon: "",
          text: "About",
          to: "About",
        }
      ]
    };
  },
};
</script>

