package com.example.analyticandroid.utils;

import android.util.Log;

public abstract class LifeCycle {

    public void initialize(){
        Log.d("LifeCycle","initialize");
    }

    public void dispose(){
        Log.d("LifeCycle","dispose");
    }

    public void suspend(){
        Log.d("LifeCycle","suspend");
    }
}
