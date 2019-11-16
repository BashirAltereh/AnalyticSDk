package com.example.analyticandroid.network;

public class WebServiceURL {
    private static String BASE_URL = "http://5.134.200.110:9090/Analytic_SDK_APIs/WS/SDK_WS/";

    private static String ADD_SESSTION = "addSession";

    private static String SUSPEND_SESSION = "Tracking/suspendSession";

    private static String CLOSE_SESSION = "Tracking/closeSession";

    public static String AddSessionUrl(){
        return BASE_URL + ADD_SESSTION;
    }

    public static String SuspendSession(){
        return BASE_URL + SUSPEND_SESSION;
    }

    public static String CloseSession(){
        return BASE_URL + CLOSE_SESSION;
    }
}
