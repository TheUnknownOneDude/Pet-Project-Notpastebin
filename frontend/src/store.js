// store/index.js
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        user: null,
        jwt: "",
    },
    mutations: {
        updateUser(state, newUser) {
            state.user = newUser;
        },

        updateJwt(state, newJwt) {
            state.jwt = newJwt;
        },
    },
    actions: {
        setUser(context, user) {
            // Вызываем мутацию для обновления состояния
            context.commit('updateUser', user);
        },

        setJwt(context, jwt) {
            // Вызываем мутацию для обновления состояния
            context.commit('updateUser', jwt);
        },
    },
    getters: {
        getUser: (state) => state.user,
        getJwt: (state) => state.jwt,
    },
});
