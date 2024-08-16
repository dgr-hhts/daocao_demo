<template>
  <el-container class="layout-container-demo">
    <el-aside width="200px">
      <el-scrollbar>
        <div class="block">
          <img src='../assets/daocao_logo.png' width="200px"  />
        </div>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="el-menu-vertical-demo"
        text-color="#fff"
        :router="true"
        @select="menuselect"
      >
      <div v-for="(value,index) in menu" :key="index">
          <el-menu-item v-if="value.children.length == 0" :index="value.index">
            <el-icon><component :is="value.icon" /></el-icon>
            <span>{{value.item}}</span>
          </el-menu-item>
          <el-sub-menu v-else :index="value.index">
          <template #title>
            <el-icon><component :is="value.icon" /></el-icon>
            <span>{{value.item}}</span>
          </template>
          <div v-for="(val,index) in value.children" :key="index">
              <el-menu-item :index="val.index">
                <el-icon><component :is="val.icon" /></el-icon>
                <span>{{val.item}}</span>
              </el-menu-item>
          </div>
        </el-sub-menu>
      </div>
      </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <span style="float: left;font-size: 20px;margin-top: 15px;">{{menuname}}</span>
        <div class="toolbar">
          <el-dropdown @command="HeaferCommand">
            <div style="margin-right: 10px">
              <el-avatar shape="square" :size="50" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item command="updatepwd">修改密码</el-dropdown-item>
                <el-dropdown-item command="info">基本信息</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <span style="margin-right: 20px;font-size: 15px">{{userinfoStore.info.nickname}}欢迎您！</span>
          <el-icon size="20" @click="loginoutfunc"><SwitchButton /></el-icon><span>退出</span>
        </div>
      </el-header>

      <el-main>
        <el-scrollbar>
          <!-- 内容 -->
          <router-view></router-view>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import router from '@/router/index'
import { ref } from 'vue'

const menu = ref([
  {
    index:'/indexpage/indexpage',
    item:'首页',
    icon:'House',
    children:[]
  },
  {
    index:'',
    item:'个人中心',
    icon:'location',
    children:[
    {
      index:'/user/updatepwd',
      item:'修改密码',
      icon:'Lock',
    },{
      index:'/user/info',
      item:'用户详情',
      icon:'User',
    }
    ]
  }
])
//获取页头
const menuname = ref('首页')
const menuselect = (val) =>{
  console.log(val);
  // menu.value.find()
  menu.value.forEach(item => {
    if(item.index == val){
      menuname.value = item.item
      return;
    }
    if(item.children.length != 0){
      item.children.forEach(i =>{
         if(i.index == val){
            menuname.value = i.item
            return;
          }
      })
    }
  });
}

const username = ref('')
import useUserinfoStore from '@/stores/userinfo'
import { userinfo } from '@/api/user/user'
const userinfoStore = useUserinfoStore()
const info = async() =>{
      await userinfo().then(res=>{
        if(res.code == 200){
          userinfoStore.setInfo(res.data)
        }
      })
}
info()

import { ElMessage, ElMessageBox } from 'element-plus'
import useTokenStore from '@/stores/token'
const usertoken = useTokenStore()
const loginoutfunc = () =>{
  ElMessageBox.confirm(
      '您确定要退出登录吗?',
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }
  )
      .then(() => {
        usertoken.removeToken()
        router.push('/login')
        ElMessage({
          type: 'success',
          message: '退出登录成功！',
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '取消退出登录!',
        })
      })
}

const HeaferCommand = (command) => {
  router.push('/user/'+command)
}

</script>

<style scoped>
.layout-container-demo{
  height: 100vh;
}
.layout-container-demo .el-header {
  position: relative;
  background-color: #f3f2fa;
  color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background:#545c64;
}
.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>
