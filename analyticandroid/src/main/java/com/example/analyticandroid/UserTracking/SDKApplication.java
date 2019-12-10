package com.example.analyticandroid.UserTracking;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.analyticandroid.SDKerrors.OnErrorSDK;
import com.example.analyticandroid.utils.Function;
import com.example.analyticandroid.utils.SDKLifeCycle;

/**
 * Created by BashirAltereh on 11/23/2019.
 */

public class SDKApplication extends Application implements LifecycleDelegate, SDKLifeCycle, OnErrorSDK {

    @Override
    public void onCreate() {
        registerLifecycleHandler(new AppLifecycleHandler(this, this, this));
//        appInitialization();
        super.onCreate();
    }

    private void registerLifecycleHandler(AppLifecycleHandler lifeCycleHandler) {
        registerActivityLifecycleCallbacks(lifeCycleHandler);
        registerComponentCallbacks(lifeCycleHandler);
    }

    @Override
    public void onAppBackgrounded() {
        Log.d("AppLifecycleHandler", "---onAppBackgrounded");

    }

    @Override
    public void onAppForegrounded() {
        Log.d("AppLifecycleHandler", "---onAppForegrounded");

    }

    @Override
    final public void initialize() {
        Toast toast = new Toast(this);
        toast.cancel();
        Toast.makeText(this, "initialize", Toast.LENGTH_SHORT).show();
        new Function().openSession(this);

    }

    @Override
    final public void dispose() {
        Toast toast = new Toast(this);
        toast.cancel();
        Toast.makeText(this, "dispose", Toast.LENGTH_SHORT).show();
        new Function().closeSession(this);
    }

    @Override
    final public void suspend() {
        Toast toast = new Toast(this);
        toast.cancel();
        Toast.makeText(this, "suspend", Toast.LENGTH_SHORT).show();
        new Function().suspendSession(this);

    }

    @Override
    public void onErrorSDKOccur(Throwable ex) {
        Log.d("AppLifecycleHandler", "------------------------------------onErrorSDKOccur: " + ex.getCause());
        new Function().closeSession(getApplicationContext());


    }
}
