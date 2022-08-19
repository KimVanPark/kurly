import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/pages/Login";
import Picking from "@/pages/Picking";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '로그인',
    component: Login
  },
  {
    path: '/picking',
    name: '피킹',
    component: Picking
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
