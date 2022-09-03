import { createApp } from 'vue'
import App from './App.vue'
import Router from './router/index'
import { createPinia } from "pinia"; // 引入 pinia
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// import axios from "axios";
import VueAxios from "vue-axios";
import axios from './axios/index'

createPinia()

const app = createApp(App);

for(const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}

app.use(Router).use(VueAxios, axios, Router).use(ElementPlus, {size: 'small', zIndex: 2000}).mount('#app');