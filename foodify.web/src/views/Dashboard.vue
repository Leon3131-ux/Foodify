<template>
  <v-main>
    <v-container>
      <h1 class="row mb-1">{{ $t("Dashboard") }}</h1>
      <div class="row mb-9">
        <v-card class="col mx-4" v-for="(item, index) in cards" :key="index">
          <v-card-text class="row">
            <div
              class="subtitle-1 col-8"
              style="border-right: 2px solid #a49394"
            >{{ $t(item.title) }}</div>
            <div class="title col-4">{{ item.amount }}</div>
          </v-card-text>
        </v-card>
      </div>
      <h2 style="color: red">{{ $t("expirationDatebald") }}</h2>
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left" v-for="(item, index) in header" :key="index">{{ item }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in compartmentexpired" :key="index">
              <td>{{ item.foodItemDto.name }}</td>
              <td>{{ getfrige(item.compartmentId) }}</td>
              <td>{{ item.itemAmount }} {{ item.unit }}</td>
              <td>{{ item.expirationDate.split("T")[0].split("-")[2] }}.{{ item.expirationDate.split("T")[0].split("-")[1] }}.{{ item.expirationDate.split("T")[0].split("-")[0] }}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-container>
  </v-main>
</template>


<script>
export default {
  components: {},
  async mounted() {
    this.getfridges();
    this.getexpiring();
  },
  computed: {
    header() {
      return ["Name", "Kühlschrank", "amount", "expirationDate"];
    },
    cards() {
      var res = [];

      let amountKühlschrank = 0;
      res.push({
        title: "Anzahl kühlschranken",
        amount: this.friges.length,
      });

      let count = 0;
      for (let frige of this.friges) {
        for (let comp of frige.compartmentDtos) {
          for (let item of comp.compartmentFoodDtos) {
            count++;
          }
        }
      }
      res.push({
        title: "Ahnzahl Esswaren",
        amount: count,
      });

      res.push({
        title: "Anzahl bald verottes Essen",
        amount: this.compartmentexpired.length,
      });
      return res;
    },
  },
  data() {
    return {
      friges: [],
      compartmentexpired: [],
    };
  },
  methods: {
    getfrige(id) {
      for (var frige of this.friges) {
        for (var comp of frige.compartmentDtos) {
          if (comp.id === id) {
            return frige.name + " -> " + comp.name;
          }
        }
      }
    },
    async getexpiring() {
      let res = await axios.get("compartment/food/expiring");
      if (res.status === 200) {
        this.compartmentexpired = res.data;
      }
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
