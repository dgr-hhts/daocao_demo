import request from '@/utils/request'

export const userlist = ()=>{
    return request.get("/sysUser/userlist")
}