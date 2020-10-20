package com.jakb.apidemo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.jakb.apidemo.Categories.ClsCategoryResponse;
import com.jakb.apidemo.Categories.InterfaceCategories;
import com.jakb.apidemo.Classes.ClsData;
import com.jakb.apidemo.Classes.ClsResponse;
import com.jakb.apidemo.Classes.InterfaceThumbImg;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Context context;
    private AppExecutor mAppExecutor;

    public Repository(Context context) {
        this.mAppExecutor = new AppExecutor();
        this.context = context;
    }


    public LiveData<ClsResponse> getThumbImg() {

        final MutableLiveData<ClsResponse> ThumbImgResponseList = new MutableLiveData<>();
        InterfaceThumbImg myInterface =
                ApiClient.getRetrofitInstance().create(InterfaceThumbImg.class);
        Call<ClsResponse> call = myInterface.GetThumbImgList();

        Log.e("--countries--", "URL: " + call.request().url());

        call.enqueue(new Callback<ClsResponse>() {
            @Override
            public void onResponse(Call<ClsResponse> call, Response<ClsResponse> response) {

                Log.e("--countries--", "onRequestResponse: " + response.code());
                if (response.body() != null && response.code() == 200) {

                    ThumbImgResponseList.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetSpecialRequestlist---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsResponse> call, Throwable t) {
                Log.e("--countries--", "onFailure: GetSpecialRequestlist" + t.getMessage());
                try {
                    ThumbImgResponseList.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--countries--", "onFailure: " + t.toString());
            }
        });

        return ThumbImgResponseList;
    }


    public LiveData<ClsCategoryResponse> getCategoriesName() {

        final MutableLiveData<ClsCategoryResponse> categoriesResponseList = new MutableLiveData<>();
        InterfaceCategories myInterface =
                ApiClient.getRetrofitInstance().create(InterfaceCategories.class);
        Call<ClsCategoryResponse> call = myInterface.GetCategoriesList();

        Log.e("--countries--", "URL: " + call.request().url());

        call.enqueue(new Callback<ClsCategoryResponse>() {
            @Override
            public void onResponse(Call<ClsCategoryResponse> call, Response<ClsCategoryResponse> response) {

                if (response.body() != null && response.code() == 200) {
                    categoriesResponseList.setValue(response.body());
                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetSpecialRequestlist---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsCategoryResponse> call, Throwable t) {
                Log.e("--countries--", "onFailure: GetSpecialRequestlist" + t.getMessage());
                try {
                    categoriesResponseList.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--countries--", "onFailure: " + t.toString());
            }
        });
        return categoriesResponseList;
    }

}
