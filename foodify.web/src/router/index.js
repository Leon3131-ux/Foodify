import Vue from 'vue'
import VueRouter from 'vue-router'
import store from './../store'

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
  {
    path: '/Admin',
    component: () => import('../Layouts/AuthLayout.vue'),
    children: [
      { path: 'Dashboard', component: () => import('../views/Admin/Dashboard.vue'), meta: { permissions: ['ADMINISTRATOR'] } },
    ]
  },

]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {  
  
  var token = null
  var jwt = store.getters["JWT"]
  if(jwt !== null){
    var base64Url = jwt.split('.')[1]
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
    token = JSON.parse(window.atob(base64))

  }

  if(to.meta.permissions !== undefined){
    var permissions = token.permissions;
    var permitted = false;
    to.meta.permissions.forEach((element) => {
      var per = permissions.some((x) => x === element);
      if (per) {
        permitted = true;
      }
    });
    if (!permitted) {
      next('Login')
    }
  }
  next()
  return
})




export default router
