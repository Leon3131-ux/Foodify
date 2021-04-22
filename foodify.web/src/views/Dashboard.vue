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
              <td>{{ item.name }}</td>
              <td>{{ item.kühlschrank }}</td>
              <td>{{ item.amount }}</td>
              <td>{{ item.expirationDate }}</td>
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
      compartmentexpired: [
        {
          name: "Kartoten",
          kühlschrank: "asd",
          amount: "14",
          expirationDate: "12.12.2004",
        },
        {
          name: "saddsa",
          kühlschrank: "JOhni",
          amount: "3",
          expirationDate: "12.12.2004",
        },
        {
          name: "Gurken",
          kühlschrank: "asdasd",
          amount: "7",
          expirationDate: "12.12.2004",
        },
      ],
    };
  },
  methods: {
    async getfridges() {
      let res = await axios.get("fridges");
      if (res.status === 200) {
        this.friges = res.data;
      }
    },
  },
};
</script>
