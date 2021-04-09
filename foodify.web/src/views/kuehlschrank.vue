<template>
  <v-main>
    <v-btn class="float-right" @click="addfridge">{{ $t("addfridge") }}</v-btn>
  </v-main>
</template>

<script>
export default {
  data() {
    return {
      friges: [],
    };
  },
  mounted() {
    this.getfridges();
  },
  methods: {
    async getfridges() {
      let res = await axios.get("frige/getfrige");

      if (res.status === 200) {
        this.friges = res.data;
      }
    },
    addfridge() {},
  },
};
</script>

<style>
</style>