import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/authenticate',
      name: 'authenticate',
      component:()=>import ('../views/SignIn.vue')
    },
    {
      path: '/register',
      name: 'register',
      component:()=>import ('../views/SignUp.vue')
    },
    {
      path: '/users/:id',
      name: 'user',
      component:()=>import ('../views/User.vue')
    },
    {
      path: '/users/create',
      name: 'create',
      component:()=>import ('../views/CreateAnnonce.vue')
    },

    {
      path: '/users/:id/update/:idAnnonce',
      name: 'update',
      component:()=>import ('../views/UpdateAnnonce.vue')
    },
    
    {
      path: '/annonce-detail/:id',
      name: 'annonceDetail',
      component: () => import('../components/AnnonceDetail.vue')
    }

  ]
})

export default router
