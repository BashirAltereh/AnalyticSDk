package com.example.analyticandroid.network;

public class WebServiceURL {
    private static String BASE_URL = "http://5.134.200.110:9090/Analytic_SDK_APIs/WS/SDK_WS/";

    private static String ADD_SESSTION = "addSession";

    public static String AddSessionUrl(){
        return BASE_URL + ADD_SESSTION;
    }
}
