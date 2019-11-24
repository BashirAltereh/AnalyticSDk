package com.example.analyticandroid.utils;

import android.util.Log;

public interface SDKLifeCycle {

    public void initialize();

    public void dispose();

    public void suspend();
}
