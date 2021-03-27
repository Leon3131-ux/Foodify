import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../Layouts/MainLayout.vue'),
    children: [
      { path: '',name:"Home", component: () => import('../views/Home.vue') },
      { path: 'about', component: () => import('../views/About.vue') },
      { path: 'Login', component: () => import('../views/Login.vue') },
      { path: 'Register', component: () => import('../views/Register.vue') },
    ]
  },
  {
    path: '/',
    component: () => import('../Layouts/AuthLayout.vue'),
    children: [
      { path: 'Dashboard', component: () => import('../views/Dashboard.vue') },
      { path: 'Kuehlschrank', component: () => import('../views/kuehlschrank.vue') },
    ]
  },

]

const router = new VueRouter({
  routes
})

export default router
