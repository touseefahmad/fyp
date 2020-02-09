package com.tikrosoft.resturantapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IntroModel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private Msg msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Msg getMsg() {
        return msg;
    }

    public void setMsg(Msg msg) {
        this.msg = msg;
    }
}
