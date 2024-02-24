import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router/index'
import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import axios from 'axios'

const app = createApp(App)
// 실제 뷰 인스턴스를 생성함

app.use(createPinia())
app.use(router)
app.use(BootstrapVue3)
app.provide('$axios', axios)

app.mount('#app')
// 렌더링할 컨테이너의 위치를 지정
