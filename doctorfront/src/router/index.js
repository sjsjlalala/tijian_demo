import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import ForgetLogin from '../components/ForgetLogin.vue'
import PhoneLogin from '../components/PhoneLogin.vue'
import OrdersList from '@/views/OrdersList.vue'
import OrdersContent from '@/views/OrdersContent.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Login',
      component:Login
    },
    {
      path: '/ForgetLogin',
      name: 'ForgetLogin',
      component:ForgetLogin
    },
    {
      path: '/PhoneLogin',
      name: 'PhoneLogin',
      component:PhoneLogin
    },
    {
      path: '/OrdersList',
      name: 'OrdersList',
      component:OrdersList
    },
    {
      path:'/OrdersContent',
      name:'OrdersContent',
      component:OrdersContent
    }
    
   
  ]
})

export default router
