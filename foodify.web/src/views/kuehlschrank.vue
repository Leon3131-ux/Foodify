<template>
  <v-main>
    <v-btn @click="$refs.createFrigeDialog.open()">{{ $t("addfridge") }}</v-btn>

    <v-row class="ma-6">
      <v-col v-for="(frige, index) in friges" :key="index">
        <v-card outlined min-height="70vh">
          <v-card-title class="headline lighten-2">
            {{ frige.name }}
          </v-card-title>
          <hr />

          <v-virtual-scroll
            min-height="67vh"
            item-height="60"
            :items="frige.items"
          >
            <template v-slot="item">
              <draggable
                v-model="frige.items"
                class="v-tabs__container"
                group="fooditems"
              >
                <v-card outlined ripple>
                  <v-card-title class="headline lighten-2">
                    {{ item.item.name }}
                  </v-card-title>
                </v-card>
              </draggable>
            </template>
          </v-virtual-scroll>
        </v-card>
      </v-col>
    </v-row>

    <createFrigeDialog ref="createFrigeDialog" @changed="getfridges" />
  </v-main>
</template>

<script>
import createFrigeDialog from "./../components/Friges/AddFrigeDialog";
import draggable from "vuedraggable";
export default {
  components: { createFrigeDialog, draggable },
  data() {
    return {
      friges: [
        {
          name: "asdad",
          items: [
            { name: "John", id: 0 },
            { name: "John1", id: 1 },
            { name: "John2", id: 2 },
          ],
        },
        {
          name: "1123",
        },
      ],
      active: 0,
      tabs: [
        {
          id: 1,
          name: "1st Tab",
          text: "This is a 1st tab",
        },
        {
          id: 2,
          name: "2nd Tab",
          text: "This is a 2nd tab",
        },
        {
          id: 3,
          name: "3rd Tab",
          text: "This is a 3rd tab",
        },
      ],
    };
  },
  mounted() {
    this.getfridges();
  },
  methods: {
    async getfridges() {
      let res = await axios.get("frige/getfriges");

      if (res.status === 200) {
        this.friges = res.data;
      }
    },
  },
};
</script>

<style>
</style>