<template>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="username" label="用户名" width="180" />
    <el-table-column prop="nickname" label="昵称" width="180" />
    <el-table-column prop="mobile" label="电话号码" />
  </el-table>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
const tableData = ref([])

import { userlist } from '@/api/user/user'

const userdata = ()=>{
  userlist().then(res=>{
    if(res.code == 200){
       ElMessage.success("获取成功!")
       tableData.value = res.data
    }else{
      ElMessage.error("获取失败!");
    }
  })
}
userdata();
</script>
