import 'es6-promise/auto';
import 'es6-object-assign/auto';
import 'core-js/shim'
import 'core-js/es5'

import Vue from 'vue'
import Vuex from 'vuex'
import VueRouter, {RouteConfig} from 'vue-router'
import BootstrapVue from 'bootstrap-vue'

import HomePage from "./pages/home/home";
import {app, rootStore} from "./common/store";

import './vendor/fontawesome/fontawesome.js';
import './vendor/fontawesome/light.js';
import './vendor/fontawesome/regular.js';
import './vendor/fontawesome/solid.js';

import '../scss/style.scss';
import SponsorPage from "./pages/sponsor/sponsor";
import LoginPage from "./pages/login/login";

require('bootstrap/dist/css/bootstrap.min.css');

Vue.config.devtools = true;
Vue.use(VueRouter);
Vue.use(Vuex);
Vue.use(BootstrapVue);

const routes: RouteConfig[] = [
    {
        path: '/login',
        name: 'login',
        component: LoginPage
    },
    {
        path: '/home',
        name: 'home',
        component: HomePage
    },
    {
        path: '/portal',
        name: 'sponsor',
        component: SponsorPage
    }
];

const router = new VueRouter({
    mode: "history",
    routes
});
router.beforeEach((to, from, next) => {
    next();
});

new Vue({
    el: '#app',
    store: rootStore.vuexStore(),
    router,
});






