import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Index from '../views/index.vue'
import UserInfo from '../views/user/info.vue'
import UpdatePWD from '../views/user/updatepwd.vue'
import IndexPage from '../views/indexpage/indexpage.vue'
import File from '../views/user/file.vue'

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
      },{
        path :'/user/file',
        component: File
      }
    ]
  }
  
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constRouter
})

export default router
