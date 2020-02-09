package com.tikrosoft.resturantapp.pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelLogin {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("user")
    @Expose
    private UserLogin user;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }
}
