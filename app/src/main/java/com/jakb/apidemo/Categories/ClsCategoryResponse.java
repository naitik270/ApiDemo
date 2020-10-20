package com.jakb.apidemo.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsCategoryResponse {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ClsCategoryData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClsCategoryData getData() {
        return data;
    }

    public void setData(ClsCategoryData data) {
        this.data = data;
    }


}
