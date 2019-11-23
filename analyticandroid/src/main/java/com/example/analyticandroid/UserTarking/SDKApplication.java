package com.example.analyticandroid.UserTarking;

import android.app.Application;
import android.util.Log;

import com.example.analyticandroid.UserTarking.LifecycleDelegate;

public class SDKApplication extends Application implements LifecycleDelegate {

    @Override
    public void onCreate() {
        registerLifecycleHandler(new AppLifecycleHandler(this));
        super.onCreate();
    }

    private void registerLifecycleHandler(AppLifecycleHandler lifeCycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler);
        registerComponentCallbacks(lifeCycleHandler);
    }

    @Override
    public void onAppBackgrounded() {
        Log.d("AppLifecycleHandler","---onAppBackgrounded");

    }

    @Override
    public void onAppForegrounded() {
        Log.d("AppLifecycleHandler","---onAppForegrounded");

    }
}
