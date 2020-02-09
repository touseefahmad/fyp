package com.tikrosoft.resturantapp.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("intro")
    @Expose
    private String intro;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
