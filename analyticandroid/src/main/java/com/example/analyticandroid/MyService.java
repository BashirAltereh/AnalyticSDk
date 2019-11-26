package com.example.analyticandroid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private static Timer timer = new Timer();
    private Context ctx;

    public MyService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("AppLifecycleHandler","-------------onStartCommand");
        startService();

        return super.onStartCommand(intent, flags, startId);
    }

    private void startService()
    {
        timer.scheduleAtFixedRate(new mainTask(), 0, 3000);
    }

    private class mainTask extends TimerTask
    {
        public void run()
        {
            toastHandler.sendEmptyMessage(0);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("AppLifecycleHandler","-------------onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private final Handler toastHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
        }
    };
}
