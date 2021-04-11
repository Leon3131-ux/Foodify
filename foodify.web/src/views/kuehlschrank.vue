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
            :item-height="virtualScrollItemHeightfixed"
            :items="frige.compartment"
          >
            <template v-slot="item">
              <v-card outlined>
                <v-card-title class="headline lighten-2">
                  {{ item.item.name }}
                </v-card-title>
                <hr />
                <draggable v-model="item.item.Fooditems" group="Fooditems">
                  <v-card
                    outlined
                    ripple
                    v-for="(fooditem, index) in item.item.Fooditems"
                    :key="index"
                  >
                    <div class="subtitle-1 ma-2">
                      {{ fooditem.name }}
                    </div>
                  </v-card>
                </draggable>
              </v-card>
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
          compartment: [
            {
              name: "John",
              Fooditems: [
                { name: "asd1" },
                { name: "asd2" },
                { name: "asd123" },
              ],
            },
            { name: "John", Fooditems: [{ name: "asd123" }] },
            { name: "John", Fooditems: [] },
          ],
        },
        {
          name: "1123",
        },
      ],
    };
  },
  computed: {
    virtualScrollItemHeightfixed() {
      var fooditemsLength = [];
      for (var value of this.friges) {
        var complength = [];
        if (value.compartment !== undefined) {
          for (var comp in value.compartment) {
            complength.push(value.compartment[comp].Fooditems.length);
          }
          fooditemsLength.push(Math.max(...complength));
        }
      }
      var maxvalue = Math.max(...fooditemsLength);
      return 100 + maxvalue * 50;
    },
  },
  mounted() {
    this.getfridges();
  },
  methods: {
    virtualScrollItemHeight(val) {
      var fooditemsLength = [];
      for (var comp in val) {
        fooditemsLength.push(val[comp].Fooditems.length);
      }
      var maxvalue = Math.max(...fooditemsLength);
      console.log(maxvalue);
      return 150;
    },
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