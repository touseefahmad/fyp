package com.tikrosoft.resturantapp.pojo.resturants;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rest {

    @SerializedName("rest_id")
    @Expose
    private String restId;
    @SerializedName("rest_name")
    @Expose
    private String restName;
    @SerializedName("rest_opening_time")
    @Expose
    private String restOpeningTime;
    @SerializedName("rest_closing_time")
    @Expose
    private String restClosingTime;

    public String getRestId() {
        return restId;
    }

    public void setRestId(String restId) {
        this.restId = restId;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestOpeningTime() {
        return restOpeningTime;
    }

    public void setRestOpeningTime(String restOpeningTime) {
        this.restOpeningTime = restOpeningTime;
    }

    public String getRestClosingTime() {
        return restClosingTime;
    }

    public void setRestClosingTime(String restClosingTime) {
        this.restClosingTime = restClosingTime;
    }

}