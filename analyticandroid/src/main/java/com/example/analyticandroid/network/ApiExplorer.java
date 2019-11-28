package com.example.analyticandroid.network;

import android.content.Context;
import android.util.Log;

import com.example.analyticandroid.IO.CacheBloc;
import com.example.analyticandroid.Tasks;
import com.example.analyticandroid.androidnetworking.AndroidNetworking;
import com.example.analyticandroid.androidnetworking.common.Priority;
import com.example.analyticandroid.androidnetworking.error.ANError;
import com.example.analyticandroid.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BashirAltereh on 10/29/2019.
 */

public class ApiExplorer {


    public static void DataLoader(final Context context, final OnDataLoaded onDataLoaded, final String url, final Map<String, String> header, final JSONObject body, final RequestPriority priority) {
        final Map<String, String> map = new HashMap<>();
        Log.d("Result_", "URL: " + url);

//        if (context != null)
//            DataFlowController.checkInternetSpeed(context);
        Log.d("Result_", "Priority" + priority.name());
//        Tasks.requestQueue.add(new RequestModel(url, header, body, priority));
        Log.d("Result_", "Queue size: " + Tasks.requestQueue.size());
        AndroidNetworking.post(url)
                .addJSONObjectBody(body)
                .setTag("test")
                .addHeaders(header)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("Result_", "response: " + response);
                        onDataLoaded.onDataLoadedSuccessfully(response);
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("Result_", "onError: " + error);
                        JSONObject cacheJson = new JSONObject();
                        try {
                            cacheJson.put("url", WebServiceURL.AddSessionUrl());
                            cacheJson.put("body", body);
                            cacheJson.put("header", header.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (context != null) {
                            CacheBloc.saveToPreferences(context, CacheBloc.REQUEST_CACHE, cacheJson.toString());

                            String r = CacheBloc.readFromPreferences(context, CacheBloc.REQUEST_CACHE, "noooooo");
                            Log.d("Result_", "cache: " + r);
                        }
                        onDataLoaded.onError(error,url, header, body, priority);
                        //TODO: save request body in cache and resend it when get connection again
                    }
                });
    }
}
