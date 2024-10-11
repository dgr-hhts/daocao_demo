import request from '@/utils/request'

export const userlist = (data)=>{
    return request.post("/sysUser/userlist",data)
}

export const userinfo = () =>{
    return request.get("/sysUser/userinfo")
}

export const adduser = (data) =>{
    return request.post("/sysUser/adduser",data)
}

export const userinfobyid = (id) =>{
    return request.get("/sysUser/userinfobyid/"+id)
}

export const updateuser = (data) =>{
    return request.put("/sysUser/updateuser",data)
}

export const deleteuser = (id) =>{
    return request.delete("/sysUser/deleteuser/"+id)
}