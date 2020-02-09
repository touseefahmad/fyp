package com.tikrosoft.resturantapp.pojo.signup;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("uemail")
    @Expose
    private String uemail;
    @SerializedName("uphone")
    @Expose
    private String uphone;
    @SerializedName("utype")
    @Expose
    private String utype;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

}