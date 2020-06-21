package com.tikrosoft.resturantapp.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tikrosoft.resturantapp.response.Rest;

import java.util.List;

public class ModelAllOrders {
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
