<template>
  <v-layout view="lHh Lpr lFf">
    <v-app-bar app color="primary" dark>
      <v-toolbar-title v-on:click="landingpage">{{ $t("Foodify") }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn
          v-for="item in nav"
          :key="item.text"
          plain
          :to="item.to"
          :title="item.title"
          >{{ item.text }}</v-btn
        >
      </v-toolbar-items>
    </v-app-bar>

    <router-view />
  </v-layout>
</template>

<script>
import Home from "@/views/Home";

export default {
  name: "MainLayout",
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
          title: "About",
          icon: "",
          text: "About",
          to: "About",
        },
      ],
    };
  },
  methods: {
    landingpage() {
      this.$router.push({ name: "Home" });
    }
  },
};
</script>

