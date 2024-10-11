<template>
  <el-row style="margin-top: 20px;">
    <el-col>
      <span style="margin-left: 40px;">用户名:</span>
  <el-input
      v-model="pageDto.username"
      style="width: 200px;margin-left: 20px;"
      placeholder="Type something"
      :prefix-icon="Search"
    />

    <span style="margin-left: 40px;">昵称:</span>
  <el-input
      v-model="pageDto.nickname"
      style="width: 200px;margin-left: 20px;"
      placeholder="Type something"
      :prefix-icon="Search"
    />

    <span style="margin-left: 40px;">电话号码:</span>
  <el-input
      v-model="pageDto.mobile"
      style="width: 200px;margin-left: 20px;"
      placeholder="Type something"
      :prefix-icon="Search"
    />

    <el-button type="primary" :icon="Search" style="margin-left: 30px;" @click="userdata()">搜索</el-button>

    <el-button type="primary" :icon="Delete" style="margin-left: 30px;" @click="reset()">重置</el-button>
    </el-col>
  </el-row>
  <el-row style="margin: 20px 20px;">
    <el-col :span = 2>
      <el-button type="primary" @click="openAddUser()">添加用户信息</el-button>
    </el-col>
    <el-col :span = 2>
      <el-button type="primary" @click="exportuser()">导出</el-button>
    </el-col>
  </el-row>
  

  <el-dialog
    v-model="dialogVisible"
    :title="title"
    width="500"
    :before-close="handleClose"
  >
    <div>
      <el-form :model="form" label-width="auto" style="max-width: 600px;margin-left: 50px;">
        <el-form-item label="用户名">
          <el-input v-model="form.username" size="large"  style="width: 240px" />
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" size="large"  style="width: 240px" />
        </el-form-item>
        <el-form-item label="性别">
          <el-select
              v-model="form.sex"
              placeholder="请选择用户性别"
              size="large"
              style="width: 240px"
          >
            <el-option label='男' :value='0' />
            <el-option label='女' :value='1' />
          </el-select>
        </el-form-item>
        <el-form-item label="头像">
          <el-upload
              class="avatar-uploader"
              action="/api/upload/uploadimage"
              :show-file-list="false"
              :headers="uploadHeaders"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
          >
          <img v-if="form.avatar" :src="form.avatar" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input v-model="form.mobile" size="large"  style="width: 240px" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" size="large"  style="width: 240px" />
        </el-form-item>
        <el-form-item label="账号状态">
          <el-select
              v-model="form.status"
              placeholder="请选择账号状态"
              size="large"
              style="width: 240px"
          >
            <el-option label='正常' :value='0' />
            <el-option label='停用' :value='1' />
          </el-select>
        </el-form-item>
      </el-form>
    </div>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submit()">确认</el-button>
      </div>
    </template>
  </el-dialog>

  <el-table :data="tableData" style="width: 100%;margin-top: 20px;" :row-class-name="tableRowClassName">
    <el-table-column prop="id" align="center" label="" width="100" />
    <el-table-column prop="username" label="用户名" align="center" width="180" />
    <el-table-column prop="nickname" label="昵称" align="center" width="180" />
    <el-table-column prop="avatar" label="头像" align="center" width="180">
      <template v-slot="scope">
        <img :src=scope.row.avatar  style="width: 70px;height: 70px;" />
      </template>
    </el-table-column>
    <el-table-column prop="sex" label="性别" align="center" width="80">
      <template v-slot="scope">
        <span v-if="scope.row.sex == 0">男</span>
        <span v-else>女</span>
      </template>
    </el-table-column>
    <el-table-column prop="email" label="邮箱" align="center" width="180" />
    <el-table-column prop="mobile" label="电话号码" align="center" width="180" />
    <el-table-column prop="status" label="状态" align="center" width="80">
      <template v-slot="scope">
        <span v-if="scope.row.status == 0">正常</span>
        <span v-else>停用</span>
      </template>
    </el-table-column>
    <el-table-column label="操作" align="center" >
      <template v-slot="scope">
        <el-button type="primary" link @click="updatebyid(scope.row.id)">编辑</el-button>
        <el-button type="danger" link @click="deleteUser(scope.row.id)">删除</el-button>
      </template>
      
    </el-table-column>
  </el-table>
  <el-pagination
      v-model:current-page="pageDto.pageNum"
      v-model:page-size="pageDto.pageSize"
      :page-sizes="[50 , 100 , 150 , 200]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      style="float: right;"
    />
</template>




<script setup>
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
const tableData = ref([])
import { Calendar, Search, Delete, Plus } from '@element-plus/icons-vue'

import { userlist, adduser, userinfobyid, updateuser, deleteuser } from '@/api/user/user'

// 导出
const exportuser = () =>{
  window.location.href = `http://localhost:5173/api/sysUser/export`
}


const total = ref()

const pageDto = ref({
  pageNum : 1,
  pageSize : 50,
  username:'',
  nickname:'',
  mobile:''
})
const userdata =async ()=>{
  await userlist(pageDto.value).then(res=>{
    if(res.code == 200){
       tableData.value = res.data.list
       total.value = res.data.total
    }else{
      ElMessage.error("获取失败!");
    }
  })
}
userdata();


const title = ref('')
const openAddUser = () =>{
  title.value = "添加用户信息"
  refrom()
  dialogVisible.value = true
}

const updatebyid =async (val) =>{
  title.value = "修改用户信息"
  dialogVisible.value = true
  await userinfobyid(val).then(res=>{
    form.value = res.data
    console.log(form.value);
    
  })
}

const dialogVisible = ref(false)

const form = ref({
  id: undefined,
  username: '',
  nickname: '',
  email: '',
  mobile: '',
  sex: undefined,
  avatar: '',
  status: undefined,
  creator:'',
  updater:''
})

const refrom = () =>{
  form.value.id = undefined
  form.value.sex = undefined
  form.value.status = undefined
  form.value.username = ''
  form.value.nickname = ''
  form.value.email = ''
  form.value.mobile = ''
  form.value.avatar = ''
  form.value.updater = ''
  form.value.creator = ''
}

const submit = async() =>{
  if(form.value.id == null){
    await adduser(form.value).then(res=>{
    if(res.code == 200){
       ElMessage.success("添加成功!");
    }else{
       ElMessage.error("添加失败!");
    }
  })
  }else{
    await updateuser(form.value).then(res=>{
      if(res.code == 200){
       ElMessage.success("修改成功!");
      }else{
         ElMessage.error("修改失败!");
      }
      info()
    })
  }
  dialogVisible.value = false
  userdata();
}



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


const deleteUser = async(val) =>{
  await deleteuser(val).then(res=>{
    if(res.code == 200){
       ElMessage.success("删除成功!");
      }else{
         ElMessage.error("删除失败!");
      }
      userdata();
  })
}

import useTokenStore from '@/stores/token'
const uploadHeaders = ref('')
const gettoken = useTokenStore()
uploadHeaders.value = {"Authorization":gettoken.token}

const handleAvatarSuccess = (
  response
) => {
  form.value.avatar = response.data
}

const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}

const handleClose = () => {
  ElMessageBox.confirm('确认要关闭吗?')
    .then(() => {
      dialogVisible.value = false
    })
    .catch(() => {
      // catch error
    })
}

const reset = () =>{
  pageDto.value.username = ""
  pageDto.value.nickname = ""
  pageDto.value.mobile = ""
  pageDto.value.pageNum = 1
  pageDto.value.pageSize = 50
  userdata();
}

const handleSizeChange = (val) => {
  console.log(`${val} items per page`)
  userdata();
}
const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`)
  userdata();
}



const tableRowClassName = ({
  row,
  rowIndex,
}) => {
  if (rowIndex % 4 === 1) {
    return 'warning-row'
  } else if (rowIndex % 4 === 3) {
    return 'success-row'
  }
  return ''
}
</script>
<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}
.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>