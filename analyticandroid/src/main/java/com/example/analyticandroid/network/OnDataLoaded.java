package com.example.analyticandroid.network;

import org.json.JSONArray;

public interface OnDataLoaded {
    public void onDataLoadedSuccessfully(JSONArray jsonArray);
    public void onError(Exception e);

}
