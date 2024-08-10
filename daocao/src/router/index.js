import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Index from '../views/index.vue'
import UserInfo from '../views/user/info.vue'
import UpdatePWD from '../views/user/updatepwd.vue'
import IndexPage from '../views/indexpage/indexpage.vue'

const constRouter = [
  //重定向
  {
    path: '',
    redirect: "/login"
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/index',
    name: 'index',
    component: Index,
    redirect:'/indexpage/indexpage',
    children:[
      {
        path :'/user/info',
        component: UserInfo
      },
      {
        path :'/user/updatepwd',
        component: UpdatePWD
      },
      {
        path :'/indexpage/indexpage',
        component: IndexPage
      }
    ]
  }
  
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constRouter
})

export default router
