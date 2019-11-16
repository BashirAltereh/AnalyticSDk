package com.example.analyticandroid.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebServiceParams {

    public static Map<String,String> getHeader(){
        final Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("language", "1");
        return header;

    }

    public static JSONObject openSessionParams(String userId,String appId,JSONObject attributes){
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", "1");
            jsonObject.put("appId", "41");
            jsonObject.put("attributes", attributes);


            Log.d("Result_", "Body: " + jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject susspendAndCloseSessionParams(String sessionKey){
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sessionKey",sessionKey);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


}
