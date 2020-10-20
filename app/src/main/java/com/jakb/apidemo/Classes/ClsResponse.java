package com.jakb.apidemo.Classes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClsResponse {

//    String status = "", message = "";
//
//    ClsData data;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public ClsData getData() {
//        return data;
//    }
//
//    public void setData(ClsData data) {
//        this.data = data;
//    }



    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ClsData data;

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

    public ClsData getData() {
        return data;
    }

    public void setData(ClsData data) {
        this.data = data;
    }


}
