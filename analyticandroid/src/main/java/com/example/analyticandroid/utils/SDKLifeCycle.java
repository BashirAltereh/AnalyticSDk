package com.example.analyticandroid.utils;

/**
 * Created by BashirAltereh on 11/2/2019.
 */

public interface SDKLifeCycle {

    void initialize();

    void dispose();

    void suspend();
}
