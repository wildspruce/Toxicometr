import Vue from 'vue'
import App from './App.vue'
import router from "@/router";
import Vuetify from "vuetify";
import VueResource from 'vue-resource'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.use(Vuetify);

const vuetify_ =  new Vuetify({
  icons: {
    iconfont: 'md', // default - only for display purposes
  },
})

Vue.use(VueResource)

Vue.config.productionTip = false

const vuetifyOptions = { }

new Vue({
  el: '#app',
  router,
  vuetify: vuetify_,
  render: h => h(App),
  vuetify: new Vuetify(vuetifyOptions)
}).$mount('#app')
