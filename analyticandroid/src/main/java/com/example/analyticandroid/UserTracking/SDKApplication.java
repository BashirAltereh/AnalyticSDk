package com.example.analyticandroid.UserTracking;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.analyticandroid.utils.Function;
import com.example.analyticandroid.utils.SDKLifeCycle;

/**
 * Created by BashirAltereh on 11/23/2019.
 */

public class SDKApplication extends Application implements LifecycleDelegate, SDKLifeCycle {

    @Override
    public void onCreate() {
        registerLifecycleHandler(new AppLifecycleHandler(this,this));
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

    @Override
    public void initialize() {
        Toast toast = new Toast(this);
        toast.cancel();
        Toast.makeText(this, "initialize", Toast.LENGTH_SHORT).show();
        new Function().openSession(this);

    }

    @Override
    public void dispose() {
        Toast toast = new Toast(this);
        toast.cancel();
        Toast.makeText(this, "dispose", Toast.LENGTH_SHORT).show();
        new Function().closeSession(this);
    }

    @Override
    public void suspend() {
        Toast toast = new Toast(this);
        toast.cancel();
        Toast.makeText(this, "suspend", Toast.LENGTH_SHORT).show();
        new Function().suspendSession(this);

    }
}
