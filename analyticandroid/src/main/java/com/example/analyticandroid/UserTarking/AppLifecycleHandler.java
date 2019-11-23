package com.example.analyticandroid.UserTarking;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.analyticandroid.androidnetworking.error.ANError;
import com.example.analyticandroid.network.ApiExplorer;
import com.example.analyticandroid.network.OnDataLoaded;
import com.example.analyticandroid.network.RequestPriority;
import com.example.analyticandroid.network.WebServiceParams;
import com.example.analyticandroid.network.WebServiceURL;

import org.json.JSONException;
import org.json.JSONObject;


public class AppLifecycleHandler implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private LifecycleDelegate lifecycleDelegate;
    private boolean appInForeground = false;
    private boolean appInBackground = false;

    AppLifecycleHandler(LifecycleDelegate lifecycleDelegate) {
        this.lifecycleDelegate = lifecycleDelegate;
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Log.d("AppLifecycleHandler", "---onActivityCreated: " + activity.getLocalClassName());

        try {
            ApiExplorer.DataLoader(activity, new OnDataLoaded() {
                @Override
                public void onDataLoadedSuccessfully(JSONObject jsonObject) {

                }

                @Override
                public void onError(ANError e) {

                }
            }, WebServiceURL.AddSessionUrl(), WebServiceParams.getHeader(), WebServiceParams.openSessionParams("", "bashir", 1, "damascus", 1, 41, new JSONObject().put("deviceModel", "bashir")), RequestPriority.IMMEDIATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d("AppLifecycleHandler", "---onActivityStarted");

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.d("AppLifecycleHandler", "---onActivityResumed");
        if (!appInForeground) {
            appInForeground = true;
            appInBackground = false;
            lifecycleDelegate.onAppForegrounded();
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.d("AppLifecycleHandler", "---onActivityPaused");

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.d("AppLifecycleHandler", "---onActivityStopped");

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Log.d("AppLifecycleHandler", "---onActivitySaveInstanceState");

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.d("AppLifecycleHandler", "---onActivityDestroyed: " + appInBackground);
        if (appInBackground) {
            //todo: call close session
            ApiExplorer.DataLoader(activity, new OnDataLoaded() {
                @Override
                public void onDataLoadedSuccessfully(JSONObject jsonObject) {

                }

                @Override
                public void onError(ANError e) {

                }
            }, WebServiceURL.CloseSession(), WebServiceParams.getHeader(), WebServiceParams.susspendAndCloseSessionParams("12345"), RequestPriority.IMMEDIATE);

        }
    }


    @Override
    public void onTrimMemory(int level) {
        Log.d("AppLifecycleHandler", "---onTrimMemory");
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            // lifecycleDelegate instance was passed in on the constructor

            ApiExplorer.DataLoader(null, new OnDataLoaded() {
                @Override
                public void onDataLoadedSuccessfully(JSONObject jsonObject) {

                }

                @Override
                public void onError(ANError e) {

                }
            }, WebServiceURL.SuspendSession(), WebServiceParams.getHeader(), WebServiceParams.susspendAndCloseSessionParams("12345"), RequestPriority.IMMEDIATE);

            appInBackground = true;
            lifecycleDelegate.onAppBackgrounded();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        Log.d("AppLifecycleHandler", "---onConfigurationChanged");

    }

    @Override
    public void onLowMemory() {
        Log.d("AppLifecycleHandler", "---onLowMemory");

    }

}
