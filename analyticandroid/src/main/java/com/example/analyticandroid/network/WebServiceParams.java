package com.example.analyticandroid.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BashirAltereh on 10/31/2019.
 */

public class WebServiceParams {

    public static Map<String, String> getHeader() {
        final Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("language", "1");
        return header;

    }

    public static JSONObject openSessionParams(String sessionKey, String username,
                                               int genderId, String address,int userId
            ,int appId, JSONObject attributes) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sessionKey", sessionKey);
            jsonObject.put("username", username);
            jsonObject.put("genderId", genderId);
            jsonObject.put("address", address);
            jsonObject.put("userId", userId);
            jsonObject.put("appId", appId);
            jsonObject.put("attributes", attributes);


            Log.d("Result_", "Body: " + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject suspendAndCloseSessionParams(String sessionKey) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sessionKey", sessionKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


}
