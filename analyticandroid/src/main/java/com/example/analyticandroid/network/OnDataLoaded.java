package com.example.analyticandroid.network;

import com.example.analyticandroid.androidnetworking.error.ANError;

import org.json.JSONObject;

public interface OnDataLoaded {
    void onDataLoadedSuccessfully(JSONObject jsonObject);
    void onError(ANError e);

}
