import Vue from 'vue'
import App from './components/App/App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);
Vue.config.productionTip = false;

Vue.filter("formatMark", value => {
    return Number(value.toFixed(2));
});

Vue.prototype.$insufficientMark = function (mark) {
    return mark < 4 ? 'insuffisient-mark' : '';
};

new Vue({
    router,
    render: h => h(App),
}).$mount('#app');
