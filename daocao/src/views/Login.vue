<template>
    <div class="login_contion">
        <div class="loginform">
            <h3 class="title">稻草管理平台</h3>
            <el-form :model="form" label-width="auto">
                <el-form-item label="用户名">
                  <el-input v-model="form.account" size="large" />
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="form.password" type="password" size="large" />
                </el-form-item>
                <el-form-item label="验证码">
                <el-row>
                    <el-col :span="12">
                        <el-input v-model="form.code" size="large" />
                    </el-col>
                    <el-col :span="12">
                        <img :src ="codeimage" style="width: 120px;height: 40px;" @click="newcode" />
                    </el-col>
                </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button class="but" type="primary" @click="loginmonthed">登录</el-button>
                </el-form-item>
            </el-form>   
        </div>
    </div>
</template>


<script setup>
import router from '@/router/index'
import { ElMessage } from 'element-plus'
import useTokenStore from '@/stores/token'
import { ref } from 'vue'

const form = ref({
    account:'',
    password:'',
    code:''
})

import { login, getcode } from '@/api/auth/index'
const loginmonthed = async()=>{
    await login(form.value).then(res=>{
        if(res.code == 200){
            ElMessage.success("登录成功！")
            router.push('/index')
            const token = useTokenStore()
            token.setToken(res.data)
        }else{
            ElMessage.error("登录失败")
        }
    });
}

const codeimage = ref('')

const newcode = async()=>{
    await getcode().then(res=>{
        console.log(res);
        
        codeimage.value = window.URL.createObjectURL(res)
    })
}
newcode()

</script>

<style lang="scss" scoped>
.login_contion{
    background-image: url('../assets/loginback.jpg');
    background-size: 100%;
    height: 100vh;
    display: flex;
    justify-content: flex-start;
    .loginform{
        width: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
        //设置换行
        flex-direction: column;
        background-color: white;

        .title{
            margin-bottom: 50px;
        }
        .but{
            margin-left: 110px;
        }
    }
}
</style>