package com.example.analyticandroid.network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceParams {
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
}
