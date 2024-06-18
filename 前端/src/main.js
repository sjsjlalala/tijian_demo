import './assets/main.css'
import 'font-awesome/css/font-awesome.min.css';
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axiosInstance from './js/axios';

const app = createApp(App)

app.use(router)
app.use(ElementPlus)
app.provide('axios', axiosInstance);


app.mount('#app')
