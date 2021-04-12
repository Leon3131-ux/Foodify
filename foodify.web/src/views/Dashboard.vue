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
            >
              {{ $t(item.title) }}
            </div>
            <div class="title col-4">{{ item.amount }}</div>
          </v-card-text>
        </v-card>
      </div>
      <h2 style="color: red">{{ $t("expirationDatebald") }}</h2>
      <v-simple-table>
        <template v-slot:default>
          <thead>
            <tr>
              <th
                class="text-left"
                v-for="(item, index) in header"
                :key="index"
              >
                {{ item }}
              </th>
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
  async mounted() {},
  computed: {
    header() {
      return ["Name", "Kühlschrank", "amount", "expirationDate"];
    },
  },
  data() {
    return {
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
      cards: [
        {
          title: "Anzahl kühlschranken",
          amount: "2",
        },
        {
          title: "Ahnzahl esswaren",
          amount: "123",
        },
        {
          title: "Sontiger scheiss",
          amount: "123",
        },
        {
          title: "test",
          amount: "123",
        },
      ],
    };
  },
  methods: {},
};
</script>
