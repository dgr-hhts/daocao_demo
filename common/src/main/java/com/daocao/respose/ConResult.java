package com.daocao.respose;

public class ConResult {
    private Object data;
    private Boolean isOk;
    private String msg;

    public ConResult(Object data) {
        this.data = data;
    }

    public ConResult(Boolean isOk, Object data) {
        this.isOk = isOk;
        this.data = data;
    }

    public ConResult(Boolean isOk) {
        this.isOk = isOk;
    }

    public ConResult(Boolean isOk, String msg) {
        this.msg = msg;
        this.isOk = isOk;
    }

    public ConResult(Boolean isOk, String msg,Object data) {
        this.data = data;
        this.isOk = isOk;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getIsOk() {
        return isOk;
    }

    public void setIsOk(Boolean code) {
        this.isOk = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
