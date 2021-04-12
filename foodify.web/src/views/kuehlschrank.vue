<template>
  <v-main>
    <v-btn @click="$refs.createFrigeDialog.open()">{{ $t("addfridge") }}</v-btn>

    <v-row class="ma-6">
      <v-col v-for="(frige, index) in friges" :key="index">
        <v-card outlined min-height="70vh">
          <v-card-title class="headline lighten-2 row">
            <div class="col-11">{{ frige.name }}</div>
            <div class="col-1">
              <v-btn elevation="2" icon medium small @click="editfrige(frige)">
                <v-icon>fas fa-edit</v-icon></v-btn
              >
            </div>
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
                    <v-card-text>
                      <div class="row">
                        <div class="subtitle-1 col-4">
                          {{ fooditem.name }}
                        </div>
                        <div class="subtitle-1 col-3">
                          {{ fooditem.amount }}
                        </div>
                        <div class="subtitle-1 col-3">
                          {{ $t("expirationDate") }}:
                          {{ fooditem.expirationDate }}
                        </div>
                        <div class="col-1">
                          <v-btn
                            elevation="2"
                            icon
                            medium
                            small
                            @click="deletefooditem(fooditem)"
                          >
                            <v-icon>fas fa-trash</v-icon></v-btn
                          >
                        </div>
                      </div>
                    </v-card-text>
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
                { name: "as11d1", amount: "0.5", expirationDate: "20.10.2130" },
                {
                  name: "asd1231",
                  amount: "0.5",
                  expirationDate: "20.10.2130",
                },
                { name: "123", amount: "0.5", expirationDate: "20.10.2130" },
              ],
            },
            {
              name: "John",
              Fooditems: [
                {
                  name: "asd1123",
                  amount: "0.5",
                  expirationDate: "20.10.2130",
                },
              ],
            },
            { name: "John", Fooditems: [] },
          ],
        },
        {
          name: "1123",
          compartment: [
            {
              name: "John1123",
              Fooditems: [],
            },
          ],
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
      console.log(maxvalue);
      return 100 + maxvalue * 90;
    },
  },
  mounted() {
    this.getfridges();
  },
  methods: {
    editfrige(frige) {},
    deletefooditem(item) {},
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