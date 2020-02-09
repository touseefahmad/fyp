package com.tikrosoft.resturantapp.pojo.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("user")
    @Expose
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
