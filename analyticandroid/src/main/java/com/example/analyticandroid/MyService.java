package com.example.analyticandroid;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.analyticandroid.androidnetworking.error.ANError;
import com.example.analyticandroid.network.ApiExplorer;
import com.example.analyticandroid.network.OnDataLoaded;
import com.example.analyticandroid.network.RequestPriority;

import org.json.JSONObject;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private static Timer timer = new Timer();
    private Context ctx;

    public MyService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AppLifecycleHandler", "-------------onStartCommand");
        startService();

        return super.onStartCommand(intent, flags, startId);
    }

    private void startService() {
        timer.scheduleAtFixedRate(new mainTask(), 0, 3000);
    }

    private class mainTask extends TimerTask {
        public void run() {
            toastHandler.sendEmptyMessage(0);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("AppLifecycleHandler", "-------------onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @SuppressLint("HandlerLeak")
    private final Handler toastHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), "test: " + Tasks.isEmpty() + " ,, " + Tasks.requestQueue.size(), Toast.LENGTH_SHORT).show();
            if (!Tasks.isEmpty()) {
            Log.d("Services_", "test: " + Tasks.isEmpty() + " ,, " + Tasks.requestQueue.size() + " , " + Tasks.getRequestFromQueue().getUrl());
                try {
                    Log.d("Services_", "cc: " + Tasks.getRequestFromQueue().toString());

                    ApiExplorer.DataLoader(getApplicationContext(), new OnDataLoaded() {
                        @Override
                        public void onDataLoadedSuccessfully(JSONObject jsonObject) {
                            Tasks.removeRequestFromQueue();
                        }

                        @Override
                        public void onError(ANError e, String url, Map<String, String> header, JSONObject body, RequestPriority priority) {

                        }
                    }, Tasks.getRequestFromQueue().getUrl(), Tasks.getRequestFromQueue().getHeader(), Tasks.getRequestFromQueue().getBody(), RequestPriority.IMMEDIATE);
                } catch (Exception e) {
                    Log.d("Services_", "Error: " + e);
                }
            }
        }
    };
}
