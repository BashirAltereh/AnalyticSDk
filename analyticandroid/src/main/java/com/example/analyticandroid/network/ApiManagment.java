package com.example.analyticandroid.network;

import android.widget.Toast;

import com.example.analyticandroid.androidnetworking.error.ANError;

import org.json.JSONObject;

public class ApiManagment implements OnDataLoaded{

    public void SendRequest(){
//        ApiExplorer.DataLoader(this);

    }

    @Override
    public void onDataLoadedSuccessfully(JSONObject jsonObject) {

    }

    @Override
    public void onError(ANError e) {

    }
}
