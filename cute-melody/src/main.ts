import { createApp } from 'vue'
import App from './App.vue'
import "./style.css"
import Router from './router'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App);

/**
 * Element-UI-Plus Icons Register
 */
for(const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}

app
    .use(Router)
    .mount('#app')
