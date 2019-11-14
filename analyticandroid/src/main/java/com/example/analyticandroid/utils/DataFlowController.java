package com.example.analyticandroid.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.analyticandroid.models.RequestModel;

import java.util.PriorityQueue;

// todo:check idle
public class DataFlowController {
    public static PriorityQueue<RequestModel> requestQueue = new PriorityQueue<>();

    //todo: under testing
    public static int checkInternetSpeed(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        NetworkCapabilities nc = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
        }
        if (nc != null) {
            int downSpeed = 0;
            int upSpeed = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                downSpeed = nc.getLinkDownstreamBandwidthKbps();
                upSpeed = nc.getLinkUpstreamBandwidthKbps();
            }

            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int linkSpeed = wifiInfo.getLinkSpeed();
            String name = wifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState()).name();
            int rssi = wifiManager.getConnectionInfo().getRssi();

            Log.d("Speed_", "downSpeed: " + downSpeed);
            Log.d("Speed_", "upSpeed: " + upSpeed);
            Log.d("Speed_", "linkSpeed: " + linkSpeed);
            Log.d("Speed_", "rssi: " + rssi);
            Log.d("Speed_", "name: " + name);
        }
        return 0;
    }

}
//D/Speed_: downSpeed: 1048576
//        upSpeed: 1048576
//        D/Speed_: linkSpeed: 72
//        rssi: -49