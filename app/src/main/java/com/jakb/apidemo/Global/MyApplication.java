package com.jakb.apidemo.Global;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ClsGlobal.getStartNetworkJOB(getApplicationContext());
    }
}