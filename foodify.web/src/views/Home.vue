<template>
  <v-main></v-main>
</template>
  <v-carousel>
  <v-carousel-item
      v-for="(sliderItems,i) in sliderItems"
      :key="i"
      :src="item.src"
      reverse-transition="fade-transition"
      transition="fade-transition"
  ></v-carousel-item>
  </v-carousel>

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
          src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
        },
      ],
    };
  },
  methods: {},
};
</script>
