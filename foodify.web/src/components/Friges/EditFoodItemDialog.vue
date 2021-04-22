<template>
  <v-dialog v-model="opened" width="500">
    <v-card>
      <v-card-title class="headline lighten-2">{{ $t("EditFoodItemDialog") }} {{ model.name }}</v-card-title>

      <v-card-text>
        <form-wrapper :validator="$v" class="row">
          <form-group name="fooditemName">
            <v-text-field
              v-model="fooditemName"
              label="fooditemName"
              slot-scope="{ attrs }"
              v-bind="attrs"
            />
          </form-group>
          <form-group name="unit">
            <v-text-field
              v-model="unit"
              label="unit"
              hint="kg"
              slot-scope="{ attrs }"
              v-bind="attrs"
            />
          </form-group>
          <form-group name="amount">
            <v-text-field v-model="amount" label="amount" slot-scope="{ attrs }" v-bind="attrs" />
          </form-group>
          <form-group name="expirationDate">
            <v-menu
              ref="menu"
              v-model="menu"
              :close-on-content-click="false"
              :return-value.sync="date"
              transition="scale-transition"
              offset-y
              min-width="auto"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="date"
                  label="Picker in menu"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="date" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
              </v-date-picker>
            </v-menu>
          </form-group>
        </form-wrapper>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" plain @click="close">{{ $t("close") }}</v-btn>
        <v-btn color="primary" plain @click="createFooditem">
          {{
          $t("add")
          }}
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { required } from "vuelidate/lib/validators";

export default {
  data() {
    return {
      menu: false,
      opened: false,
      model: {},
      date: null,
      amount: 0,
      fooditemName: "",
      fooditemid: 0,
      id: 0,
      compartmentid: 0,
      unit: "",
    };
  },
  validations: {
    fooditemName: { required },
    amount: { required },
    unit: { required },
  },
  methods: {
    reset() {
      this.model = {};
      this.date = "";
      this.amount = 0;
      this.fooditemid = 0;
      this.fooditemName = "";
      this.id = 0;
      this.compartmentid = 0;
    },
    close() {
      this.opened = false;
    },
    open(item) {
      this.reset();
      this.model = item;
      this.compartmentid = item.id;
      this.opened = true;
    },
    editfooditem(fooditem, compartment) {
      let datefooditem = fooditem.expirationDate.split("T")[0].split("-");
      this.reset();
      this.model = compartment;
      this.compartmentid = compartment.id;
      this.date =
        datefooditem[0] + "-" + datefooditem[1] + "-" + datefooditem[2];
      this.amount = fooditem.itemAmount;
      this.fooditemName = fooditem.foodItemDto.name;
      this.fooditemid = fooditem.foodItemDto.id;
      this.unit = fooditem.unit;
      this.opened = true;
    },
    async createFooditem() {
      let res = await axios.post("foodItem/save", {
        id: this.fooditemid,
        name: this.fooditemName,
      });
      if (res.status === 200) {
        this.fooditemid = res.data.id;
        let dateitem = this.date.split("-");
        res = await axios.post("compartment/food/save", {
          compartmentId: this.compartmentid,
          foodItemId: this.fooditemid,
          itemAmount: this.amount,
          unit: this.unit,
          expirationDate: dateitem[2] + "/" + dateitem[1] + "/" + dateitem[0],
        });
        this.$emit("changed");
        this.close();
      }
    },
  },
};
</script>

<style>
</style>