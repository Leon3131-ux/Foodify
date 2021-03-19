import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../Layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('../views/Home.vue') },
      { path: 'about', component: () => import('../views/About.vue') }
    ]
  },

]

const router = new VueRouter({
  routes
})

export default router
