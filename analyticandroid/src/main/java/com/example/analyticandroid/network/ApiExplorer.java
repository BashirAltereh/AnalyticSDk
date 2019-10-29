package com.example.analyticandroid.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiExplorer extends AsyncTask<String, Void, Bitmap> {
    public static void craeteRequest(Context context) {
        HttpURLConnection connection = null;
        Toast.makeText(context,"createRequest",Toast.LENGTH_SHORT).show();
        try {
            Log.d("Result_","11111");
            connection = (HttpURLConnection) (new URL("https://api.androidhive.info/json/contacts.json")).openConnection();
            Log.d("Result_","22222");
            connection.setRequestMethod("GET"); // or post
            Log.d("Result_","33333");
            connection.connect();
            Log.d("Result_","44444");
            InputStream is = connection.getInputStream();
            Log.d("Result_","Response: "+is.toString());
        } catch (Exception e) {
            Log.d("Result_","Error: "+e);
        }
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        HttpURLConnection connection = null;

        try {
            Log.d("Result_","11111");
            connection = (HttpURLConnection) (new URL("https://api.androidhive.info/json/contacts.json")).openConnection();
            Log.d("Result_","22222");
            connection.setRequestMethod("GET"); // or post
            Log.d("Result_","33333");
            connection.connect();
            Log.d("Result_","44444");
            InputStream is = connection.getInputStream();
            Log.d("Result_","Response: "+is.toString());
        } catch (Exception e) {
            Log.d("Result_","Error: "+e);
        }
        return null;
    }
}
