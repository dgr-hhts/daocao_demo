import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import { createPersistedState } from 'pinia-persistedstate-plugin'

const persist = createPersistedState()

const pinia = createPinia()
pinia.use(persist)

const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.use(pinia)
app.mount('#app')


for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}