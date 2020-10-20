package com.jakb.apidemo.Classes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceThumbImg {


    @GET("stickers/m/list")
    Call<ClsResponse> GetThumbImgList();

}
