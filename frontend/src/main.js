import Vue from 'vue'
import App from './App.vue'
import router from './router';
import Vuex from 'vuex';
import store from "./store";

Vue.config.productionTip = false


Vue.use(Vuex);


new Vue({
    render: h => h(App),
    router,
    store
}).$mount('#app')
