package com.tikrosoft.resturantapp.pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin {

    @SerializedName("uid")
    @Expose
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
