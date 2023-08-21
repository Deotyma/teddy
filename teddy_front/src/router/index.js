import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SignIn from '../views/SignIn.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/sign-in',
      name: 'signIn',
      component:()=>import ('../views/SignIn.vue')
    },
    {
      path: '/sign-up',
      name: 'signUp',
      component:()=>import ('../views/SignUp.vue')
    }
  ]
})

export default router
