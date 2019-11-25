package com.example.analyticandroid.network;

import com.example.analyticandroid.androidnetworking.error.ANError;

import org.json.JSONObject;

/**
 * Created by BashirAltereh on 10/31/2019.
 */

public interface OnDataLoaded {
    void onDataLoadedSuccessfully(JSONObject jsonObject);
    void onError(ANError e);

}
