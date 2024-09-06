import router from '@/router/index.js';
import axios from 'axios'
import { ElMessage } from 'element-plus';
import useTokenStore from '@/stores/token.js'

const request= axios.create({
    baseURL: '/api'
})


axios.defaults.headers['Content-Type'] = 'application/json?charset=UTF-8'

//请求拦截器
request.interceptors.request.use((config)=>{
    let tokenStore = useTokenStore()
    if(tokenStore.token){
        config.headers['Authorization'] = tokenStore.token;
    }
    return config;
},(error)=>{
    return Promise.reject(error);
})

//响应拦截器
request.interceptors.response.use((response)=>{
    let {code,msg} = response.data;
    
    if(code == 200){
        return response.data;
    }else if(code == 403){
        ElMessage.error("登录过期！")
        window.sessionStorage.clear();
        router.push('/login');
        return Promise.reject(msg);
    }else if(code == 201){
        ElMessage.error(msg)
        window.sessionStorage.clear();
        router.push('/login');
        return Promise.reject(msg);
    }else{
        ElMessage.error("服务器错误！")
        window.sessionStorage.clear();
        router.push('/login');
        return Promise.reject(msg);
    }
},(error)=>{
    if(error.response.status == 401){
        ElMessage.error("未登录，请先登录!")
        router.push('/login');
    }
    window.sessionStorage.clear();
    router.push('/login');
    return Promise.reject(error);
})


export default request