package com.tikrosoft.resturantapp.pojo.resturants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelResturants {

    @SerializedName("rest")
    @Expose
    private List<Rest> rest = null;

    public List<Rest> getRest() {
        return rest;
    }

    public void setRest(List<Rest> rest) {
        this.rest = rest;
    }
}