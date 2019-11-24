package com.example.analyticandroid.UserTracking;

import android.app.Application;
import android.content.Context;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

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
        Toast.makeText(this, "SuspendSession", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAppForegrounded() {
        Log.d("AppLifecycleHandler","---onAppForegrounded");

    }
}
