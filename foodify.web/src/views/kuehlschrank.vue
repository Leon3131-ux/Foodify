<template>
  <v-main>
    <v-btn @click="$refs.createFrigeDialog.open()">{{ $t("addfridge") }}</v-btn>
    <v-row class="ma-6">
      <v-col v-for="(frige, index) in friges" :key="index">
        <v-card outlined min-height="70vh">
          <v-card-title class="headline lighten-2 row justify-space-between">
            <div class="col-10">{{ frige.name }}</div>
            <div class="col-2">
              <v-btn elevation="2" icon medium small @click="editfrige(frige)">
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-btn elevation="2" icon medium small @click="deletefrige(frige)">
                <v-icon>mdi-delete</v-icon>
              </v-btn>
            </div>
          </v-card-title>

          <hr />

          <v-virtual-scroll
            min-height="67vh"
            :item-height="virtualScrollItemHeightfixed"
            :items="frige.compartmentDtos"
          >
            <template v-slot="item">
              <v-card outlined>
                <v-card-title class="headline lighten-2">
                  {{ item.item.name }}
                  <v-row justify="end">
                    <v-btn
                      elevation="2"
                      icon
                      medium
                      small
                      @click="openeditFoodItemDialog(item.item)"
                    >
                      <v-icon>mdi-plus</v-icon>
                    </v-btn>
                  </v-row>
                </v-card-title>
                <hr />
                <draggable
                  v-model="item.item.compartmentFoodDtos"
                  group="Fooditems"
                  @change="changed"
                >
                  <v-card
                    outlined
                    ripple
                    v-for="(fooditem, index) in item.item.compartmentFoodDtos"
                    :key="index"
                  >
                    <v-card-text>
                      <div class="row">
                        <div class="subtitle-1 col-4">{{ fooditem.foodItemDto.name }}</div>
                        <div class="subtitle-1 col-3">{{ fooditem.itemAmount }} {{ fooditem.unit }}</div>
                        <div class="subtitle-1 col-3">
                          {{ $t("expirationDate") }}:
                          {{ fooditem.expirationDate.split("T")[0] }}
                        </div>
                        <div class="col-1">
                          <v-btn elevation="2" icon medium small @click="deletefooditem(fooditem)">
                            <v-icon>mdi-delete</v-icon>
                          </v-btn>
                          <v-btn
                            elevation="2"
                            icon
                            medium
                            small
                            @click="editfooditem(fooditem, item.item)"
                          >
                            <v-icon>mdi-pencil</v-icon>
                          </v-btn>
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
    <editFoodItemDialog ref="editFoodItemDialog" @changed="getfridges" />
  </v-main>
</template>

<script>
import createFrigeDialog from "./../components/Friges/AddFrigeDialog";
import draggable from "vuedraggable";
import EditFoodItemDialog from "./../components/Friges/EditFoodItemDialog";

export default {
  components: { createFrigeDialog, draggable, EditFoodItemDialog },
  data() {
    return {
      friges: [],
    };
  },
  computed: {
    virtualScrollItemHeightfixed() {
      var fooditemsLength = [];
      for (var value of this.friges) {
        var complength = [];
        if (value.compartmentDtos !== undefined) {
          for (var comp in value.compartmentDtos) {
            complength.push(
              value.compartmentDtos[comp].compartmentFoodDtos.length
            );
          }
          fooditemsLength.push(Math.max(...complength));
        }
      }
      var maxvalue = Math.max(...fooditemsLength);
      return 100 + maxvalue * 90;
    },
  },
  mounted() {
    this.getfridges();
  },
  methods: {
    async deletefrige(frige) {
      let res = await axios.delete("fridge/" + frige.id);
      this.getfridges();
    },
    editfrige(frige) {
      this.$refs.createFrigeDialog.edit(frige);
    },
    editfooditem(fooditem, compartment) {
      this.$refs.editFoodItemDialog.editfooditem(fooditem, compartment);
    },
    openeditFoodItemDialog(item) {
      this.$refs.editFoodItemDialog.open(item);
    },
    async changed() {
      for (let frige of this.friges) {
        let res = await axios.post("fridge/save", {
          id: frige.id,
          name: frige.name,
        });
        if (res.status === 200) {
          for (let compartment of frige.compartmentDtos) {
            res = await axios.post("compartment/save", {
              id: compartment.id,
              name: compartment.name,
              fridgeId: compartment.fridgeId,
            });
            if (res.status === 200) {
              for (let food of compartment.compartmentFoodDtos) {
                let datefooditem = food.expirationDate.split("T")[0].split("-");
                res = await axios.post("compartment/food/save", {
                  id: food.id,
                  compartmentId: compartment.id,
                  foodItemId: food.foodItemDto.id,
                  itemAmount: food.itemAmount,
                  unit: food.unit,
                  expirationDate:
                    datefooditem[2] +
                    "/" +
                    datefooditem[1] +
                    "/" +
                    datefooditem[0],
                });
              }
            }
          }
        }
      }
      this.getfridges();
    },
    async deletefooditem(item) {
      let res = await axios.delete("compartment/food/" + item.id);
      this.getfridges();
    },
    async getfridges() {
      let res = await axios.get("fridges");
      if (res.status === 200) {
        this.friges = res.data;
      }
    },
  },
};
</script>

<style>
</style>