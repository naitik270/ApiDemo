package com.jakb.apidemo.Categories;


import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceCategories {

    @GET("categories/m/list")
    Call<ClsCategoryResponse> GetCategoriesList();

}
