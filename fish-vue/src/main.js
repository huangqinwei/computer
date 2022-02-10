// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';

Vue.use(router);
Vue.use(ElementUI);
Vue.prototype.$axios = axios;
axios.defaults.baseURL = 'http://localhost:8082/';

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  axios,
  render: h => h(App)
})


import echarts from 'echarts'
Vue.prototype.$echarts = echarts
