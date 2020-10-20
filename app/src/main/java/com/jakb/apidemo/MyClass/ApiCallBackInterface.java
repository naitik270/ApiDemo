package com.jakb.apidemo.MyClass;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiCallBackInterface {

    @GET
    Call<MainResponse> getImagesList(@Url String url);


}
