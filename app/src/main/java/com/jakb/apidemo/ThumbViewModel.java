package com.jakb.apidemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.jakb.apidemo.Categories.ClsCategoryResponse;
import com.jakb.apidemo.Classes.ClsResponse;

public class ThumbViewModel extends AndroidViewModel {

    private Repository repository;

    public ThumbViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public LiveData<ClsResponse> getThumbImgList() {
        return repository.getThumbImg();
    }

    public LiveData<ClsCategoryResponse> getCategoriesName() {
        return repository.getCategoriesName();
    }
}
