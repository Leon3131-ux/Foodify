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
  computed: {
    nav() {
      var nav = [
        {
          icon: "",
          text: this.$t("kuehlschrank"),
          to: "Kuehlschrank",
        },
        {
          icon: "",
          text: this.$t("Admin"),
          to: "Admin/Dashboard",
          permissions: ["ADMINISTRATOR"],
        },
      ];
      for (var id in nav) {
        var item = nav[id];
        console.log(this.permissions);
        if (item.permissions != undefined) {
          var permitted = false;
          item.permissions.forEach((element) => {
            var per = this.permissions.some((x) => x === element);
            if (per) {
              permitted = true;
            }
          });
          if (!permitted) {
            nav = nav.filter(function (value, index, arr) {
              return index != id;
            });
          }
        }
      }
      return nav;
    },
  },
  data() {
    return {
      permissions: [],
    };
  },
  mounted() {
    var jwt = this.$store.getters["JWT"];
    if (jwt !== null) {
      var base64Url = jwt.split(".")[1];
      var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
      var token = JSON.parse(window.atob(base64));
      this.permissions = token.permissions;
    } else {
      this.logout();
    }
  },
};
</script>

