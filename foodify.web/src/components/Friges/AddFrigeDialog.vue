<template>
  <v-dialog v-model="opened" width="500">
    <v-card>
      <v-card-title class="headline lighten-2">
        {{ $t("AddEditFrigeDialog") }}
      </v-card-title>

      <v-card-text>
        <form-wrapper :validator="$v">
          <form-group name="frigename">
            <v-text-field
              v-model="frigename"
              label="frigename"
              slot-scope="{ attrs }"
              v-bind="attrs"
            />
          </form-group>
          <v-row justify="end">
            <v-btn elevation="2" icon medium small @click="addcompartment">
              <v-icon>fas fa-plus</v-icon></v-btn
            >
          </v-row>

          <div class="title">{{ $t("compartments") }}</div>
          <div v-for="(item, index) in compartment" :key="index">
            <form-group name="compartmenname">
              <v-text-field
                v-model="item.name"
                :label="$t('compartment')"
                slot-scope="{ attrs }"
                v-bind="attrs"
                append-icon="far fa-trash-alt"
                @click:append="deletecompartment(index)"
              >
                <template v-slot:prepend>
                  {{ index + 1 }}
                </template>
              </v-text-field>
            </form-group>
          </div>
        </form-wrapper>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" plain @click="close">{{ $t("close") }}</v-btn>
        <v-btn color="primary" plain @click="createFrige">{{
          $t("add")
        }}</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { required } from "vuelidate/lib/validators";

export default {
  data() {
    return {
      opened: false,
      frigename: "",
      frigeid: 0,
      compartment: [],
    };
  },
  validations: {
    frigename: { required },
    compartmenname: { required },
  },
  methods: {
    close() {
      this.frigename = "";
      this.compartment = [];
      this.frigeid = 0;
      this.opened = false;
    },
    open() {
      this.opened = true;
    },
    deletecompartment(id) {
      var index = -1;
      this.compartment = this.compartment.filter((x) => {
        index++;
        console.log(id);
        return index !== id;
      });
    },
    addcompartment() {
      this.compartment.push({
        id: 0,
        name: "",
        fridgeId: this.frigeid,
      });
    },
    async createFrige() {
      let res = await axios.post("fridge/save", {
        id: this.frigeid,
        name: this.frigename,
      });
      if (res.status === 200) {
        this.frigeid = res.data.id;
        for (const item of this.compartment) {
          res = await axios.post("compartment/save", {
            id: item.id,
            name: item.name,
            fridgeId: this.frigeid,
          });
        }
      }
      this.$emit("changed");
      this.close();
    },
  },
};
</script>

<style>
</style>