<template>
  <v-layout view="lHh Lpr lFf">
    <v-app-bar app color="primary" dark>
      <v-toolbar-title>{{ $t("Foodify") }}</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-btn v-for="item in nav" :key="item.text" plain :to="item.to">{{
          item.text
        }}</v-btn>
        <v-btn plain @click="logout">{{ $t("Logout") }}</v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <router-view />
  </v-layout>
</template>

<script>
export default {
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push({ name: "Home" });
    },
  },
  data() {
    return {
      nav: [
        {
          icon: "",
          text: this.$t("kuehlschrank"),
          to: "Kuehlschrank",
        },
      ],
    };
  },
};
</script>

