<template>
  <v-main>
    <v-row>
      <v-col cols="12">
        <v-card color="black">
        <v-img
            contain
            height="450px"
            float="right"
            src="..\assets\FoodifyLogo.png"
        ></v-img>
        </v-card>
      </v-col>
    </v-row>
    <v-carousel
        :continuous="true"
        :cycle="true"
        height="850px">
      <v-carousel-item
          v-for="(sliderItems,i) in sliderItems"
          :key="i"
          :src="sliderItems.src"
          reverse-transition="fade-transition"
          transition="fade-transition"
      ></v-carousel-item>
    </v-carousel>
  </v-main>
</template>

<script>
import { required } from "vuelidate/lib/validators";
export default {
  name: "Home",
  validations: {
    password: {
      required,
    },
    username: {
      required,
    },
  },
  components: {},
  async mounted() {},
  data() {
    return {
      sliderItems: [
        {
          src: 'https://health.clevelandclinic.org/wp-content/uploads/sites/3/2019/06/cropped-GettyImages-643764514.jpg',
        },
        {
          src: 'https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F23%2F2020%2F09%2F17%2FVitamin-D-Foods-2000.jpg',
        },
        {
          src: 'https://media-exp1.licdn.com/dms/image/C561BAQE0SDXypNII7Q/company-background_10000/0/1519800787024?e=2159024400&v=beta&t=34Njc44a_T3LRURu9_GdOrYOIndKSeCz0O5ZYnJ1K78',
        },
      ],
    };
  },
  methods: {},
};
</script>
