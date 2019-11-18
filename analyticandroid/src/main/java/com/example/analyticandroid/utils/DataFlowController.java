package com.example.analyticandroid.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.analyticandroid.models.RequestModel;

import java.util.PriorityQueue;

// todo:check idle
public class DataFlowController {
    public static PriorityQueue<RequestModel> requestQueue = new PriorityQueue<>();

    private static Handler mHandler = new Handler();
    private static long mPreviousRxBytes = 0;
    private static  long mPreviousTxBytes = 0;
    private static long mStartRX = 0;
    private static  long mStartTX = 0;
    static  DataTraffic dataTrafficLL;
    public static void trafficStats(Context context, final DataTraffic dataTraffic){
        dataTrafficLL = dataTraffic;
        mStartRX = TrafficStats.getTotalRxBytes();
        mStartTX = TrafficStats.getTotalTxBytes();

        if (mStartRX == TrafficStats.UNSUPPORTED || mStartTX == TrafficStats.UNSUPPORTED) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Uh Oh!");
            alert.setMessage("Your device does not support traffic stat monitoring.");
            alert.show();
        } else {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    long rxBytes = TrafficStats.getTotalRxBytes() - mStartRX;
//            RX.setText(Long.toString(rxBytes));
                    Log.d("Speed_ traffic","rxBytes: "+rxBytes);
                    long txBytes = TrafficStats.getTotalTxBytes() - mStartTX;
                    dataTraffic.OnDataTraffic(rxBytes,txBytes);
//            TX.setText(Long.toString(txBytes));
                    Log.d("Speed_ traffic","txBytes: "+rxBytes);
                    mHandler.postDelayed(mRunnable, 1000);
                }
            }, 1000);
        }
    }
    private static final Runnable mRunnable = new Runnable() {
        public void run() {
            long rxBytes = TrafficStats.getTotalRxBytes() - mStartRX;
//            RX.setText(Long.toString(rxBytes));
            Log.d("Speed_ traffic","rxBytes: "+rxBytes + " , mPreviousRxBytes: "+mPreviousRxBytes);
            long txBytes = TrafficStats.getTotalTxBytes() - mStartTX;
            mPreviousRxBytes = rxBytes;
            mPreviousTxBytes = txBytes;
            rxBytes -= mPreviousRxBytes;
            txBytes -= mPreviousTxBytes;
            dataTrafficLL.OnDataTraffic(rxBytes,txBytes);
//            TX.setText(Long.toString(txBytes));
            Log.d("Speed_ traffic","txBytes: "+rxBytes);
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

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

   public interface DataTraffic{
        void OnDataTraffic(long rxBytes , long txBytes);
    }

}
//D/Speed_: downSpeed: 1048576
//        upSpeed: 1048576
//        D/Speed_: linkSpeed: 72
//        rssi: -49
