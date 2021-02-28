<template>
  <q-select
    :options="options"
    v-model="lang"
    class="white-text"
    hide-underline
  />
</template>

<script>
export default {
  name: "LanguageSwitcher",
  data() {
    return {
      lang: this.$i18n.locale,
    };
  },
  computed: {
    options() {
      return [
        { label: "English (US)", value: "en-us" },
        { label: "Deutsch (Schweiz)", value: "de" },
      ];
    },
  },
  watch: {
    lang() {
      // dynamic import, so loading on demand only
      import(`quasar/lang/${this.lang.value}`).then((l) => {
        //console.log(l)
        this.$i18n.locale = this.lang.value;
        this.$q.lang.set(l.default);
        this.$store.dispatch("global/changeLocale", this.lang.value);
      });
    },
  },
};
</script>
