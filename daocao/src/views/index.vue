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
      >
      <el-menu-item index="/indexpage/indexpage">
          <el-icon><House /></el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-sub-menu index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <span>个人中心</span>
          </template>
            <el-menu-item index="/user/updatepwd">修改密码</el-menu-item>
            <el-menu-item index="/user/info">基本信息</el-menu-item>
        </el-sub-menu>
        
      </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <div class="toolbar">
          <el-dropdown @command="HeaferCommand">
            <div style="margin-right: 10px">
              <el-avatar shape="square" :size="50" :src="circleUrl" />
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
