import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import index from '../views/Index.vue'
import appointment from '../views/Appointment.vue'
import hospital from '../views/Hospital.vue'
import setmeal from '../views/Setmeal.vue'
import selectdata from '../views/Selectdata.vue'
import confirmorder from '../views/Confirmorder.vue'
import appointmentsuccess from '../views/AppointmentSuccess.vue'
import appointmentlist from '../views/Appointmentlist.vue'
import selectdate from '../views/Selectdate.vue'
import appointmentok from '../views/Appointmentok.vue'
import appointmentcancel from '../views/Appointmentcancel.vue'
import personal from '../views/Personal.vue'
import reportlist from '../views/Reportlist.vue'
import report from '../views/Report.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/register',
      name:'register',
      component: Register
    },
    {
      path: '/index',
      name: 'index',
      component: index
    },
    {
      path: '/appointment',
      name: 'appointment',
      component: appointment
    },
    {
      path: '/hospital',
      name: 'hospital',
      component: hospital
    },
    {
      path: '/setmeal',
      name:'setmeal',
      component: setmeal
    },
    {
      path: '/selectdata',  
      name:'selectdata',
      component: selectdata
    },
    {
      path: '/confirmorder',
      name:'confirmorder',
      component: confirmorder
    },
    {
      path: '/appointmentsuccess',
      name:'appointmentsuccess',
      component: appointmentsuccess
    },
    {
      path: '/appointmentlist',
      name:'appointmentlist',
      component: appointmentlist
    },
    {
      path: '/selectdate',
      name:'selectdate',
      component: selectdate
    },
    {
      path: '/appointmentok',
      name:'appointmentok',
      component: appointmentok
    },
    {
      path: '/appointmentcancel',
      name:'appointmentcancel',
      component: appointmentcancel
    },
    {
      path: '/personal',
      name:'personal',
      component: personal
    },
    {
      path: '/reportlist',
      name:'reportlist',
      component: reportlist
    },
    {
      path: '/report',
      name:'report',
      component: report
    }
  ]
})

export default router
