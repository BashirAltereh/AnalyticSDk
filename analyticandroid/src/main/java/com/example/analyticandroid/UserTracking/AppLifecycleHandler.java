package com.example.analyticandroid.UserTracking;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.analyticandroid.SDKerrors.OnErrorSDK;
import com.example.analyticandroid.utils.SDKLifeCycle;

/**
 * Created by BashirAltereh on 11/23/2019.
 */

public class AppLifecycleHandler implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private LifecycleDelegate lifecycleDelegate;
    private SDKLifeCycle sdkLifeCycle;
    private OnErrorSDK onErrorSDK;

    private boolean appInForeground = false;
    private boolean appInBackground = true;

    AppLifecycleHandler(LifecycleDelegate lifecycleDelegate, SDKLifeCycle sdkLifeCycle,OnErrorSDK onErrorSDK) {
        this.lifecycleDelegate = lifecycleDelegate;
        this.sdkLifeCycle = sdkLifeCycle;
        this.onErrorSDK = onErrorSDK;

        appInitialization();
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        Log.d("AppLifecycleHandler", "---onActivityCreated: " + activity.getLocalClassName());

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d("AppLifecycleHandler", "---onActivityStarted");
        if (appInBackground) {
                sdkLifeCycle.initialize();
                Log.d("AppLifecycleHandler", "-----------------------------------------------------OpenSession");
        }
        appInBackground = false;
        appInForeground = true;

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
        PowerManager pm = (PowerManager) ((Context) activity).getSystemService(Context.POWER_SERVICE);
        assert pm != null;
        boolean isScreenOn = pm.isScreenOn();
        Log.d("AppLifecycleHandler", "---onActivityStopped: " + isScreenOn);
        if (!isScreenOn) {
            sdkLifeCycle.suspend();
            Log.d("AppLifecycleHandler", "-----------------------------------------------------SuspendSession");
            appInBackground = true;
            appInForeground = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        Log.d("AppLifecycleHandler", "---onActivitySaveInstanceState");

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.d("AppLifecycleHandler", "---onActivityDestroyed: " + appInBackground);
        if (appInBackground) {
            sdkLifeCycle.dispose();
            Log.d("AppLifecycleHandler", "-----------------------------------------------------CloseSession");

        }
    }


    @Override
    public void onTrimMemory(int level) {
        Log.d("AppLifecycleHandler", "---onTrimMemory");
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            // lifecycleDelegate instance was passed in on the constructor
            Log.d("AppLifecycleHandler", "-----------------------------------------------------SuspendSession");
            sdkLifeCycle.suspend();
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

    // Catch all exception
    private void appInitialization() {
        defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(_unCaughtExceptionHandler);
    }

    private Thread.UncaughtExceptionHandler defaultUEH;

    // handler listener
    private Thread.UncaughtExceptionHandler _unCaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            ex.printStackTrace();
            onErrorSDK.onErrorSDKOccur(ex);

        }
    };
}
