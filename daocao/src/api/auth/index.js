import request from "@/utils/request";

export const login = (data)=>{
    return request.post("/auth/sys",data)
}

export const getcode = () =>{
    var r = Math.random();//codeId，随机数
    return request.get('/upload/code?r='+r,{responseType:'blob'})
}