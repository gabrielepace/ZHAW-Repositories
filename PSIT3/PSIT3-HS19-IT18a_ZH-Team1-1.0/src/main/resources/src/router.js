import Vue from 'vue'
import Router from 'vue-router'
import Overview from './components/overview/Overview.vue'
import Login from './components/login/Login.vue'
import Register from './components/register/Register.vue'

Vue.use(Router);

export default new Router({
    mode: 'history',
    withCredentials: false,
    devServer: {
        proxy: 'http://localhost:8080',
    },
    routes: [
        {
            path: '/',
            name: 'overview',
            component: Overview
        },
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        {
            path: '/register',
            name: 'register',
            component: Register
        }
    ]
})
