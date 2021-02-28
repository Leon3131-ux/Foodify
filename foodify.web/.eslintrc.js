module.exports = {
  root: true,

  parserOptions: {
    parser: 'babel-eslint',
    sourceType: 'module'
  },

  env: {
    browser: true
  },

  extends: [
    // https://github.com/vuejs/eslint-plugin-vue#priority-a-essential-error-prevention
    // consider switching to `plugin:vue/strongly-recommended` or `plugin:vue/recommended` for stricter rules.
    'plugin:vue/essential',
    // https://github.com/standard/standard/blob/master/docs/RULES-en.md
    //'standard'
    'eslint:recommended'
  ],

  // required to lint *.vue files
  plugins: [
    'vue'
  ],

  globals: {
    'ga': true, // Google Analytics
    'cordova': true,
    '__statics': true,
    'process': true,
    'Capacitor': true,
    'chrome': true
  },

  // add your custom rules here
  rules: {
    // allow async-await
    'generator-star-spacing': 'off',

    // // allow debugger during development
    // 'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0,

    'no-console' : 'off',
    'no-unused-vars' : 'off',
    'no-undef' : 'off',
    'no-async-in-computed-properties' : 'off',
    'no-case-declarations' : 'off',
    'no-irregular-whitespace': 'off'
  }
}
