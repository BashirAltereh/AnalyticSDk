package com.example.analyticandroid.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiExplorer extends AsyncTask<String, Void, Bitmap> {

    public static void createGETRequest(Context context , final OnDataLoaded onDataLoaded) {
        ApiExplorer.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Result_", "run...");
                HttpURLConnection connection = null;
                try {
                    Log.d("Result_", "11111");
                    connection = (HttpURLConnection) (new URL("https://api.androidhive.info/json/contacts.json")).openConnection();
                    Log.d("Result_", "22222");
                    connection.setRequestMethod("GET"); // or post
                    Log.d("Result_", "33333");
                    connection.connect();
                    Log.d("Result_", "44444");
                    InputStream is = connection.getInputStream();
                    Log.d("Result_", "Response: " + is);
                    int i;
                    String s = "";
                    while ((i = is.read()) != -1) {

                        // converts integer to character
                        char c = (char) i;

                        // prints character
                        s += c;
                        Log.d("Result_", "Response: " + c);
                    }
                    Log.d("Result_", "Response: " + new JSONArray(s));

                    onDataLoaded.onDataLoadedSuccessfully(new JSONArray(s));

                } catch (Exception e) {
                    Log.d("Result_", "Error: " + e);
                    onDataLoaded.onError(e);

                }
            }
        });

    }

    public static void createPOSTRequest(Context context) {
        ApiExplorer.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Result_", "run...");

                HttpURLConnection connection = null;
                try {
                    Log.d("Result_", "11111 POST");
                    connection = (HttpURLConnection) (new URL("https://api.androidhive.info/json/contacts.json")).openConnection();
                    Log.d("Result_", "22222 POST");
                    connection.setRequestMethod("POST"); // or post
                    connection.setDoOutput(true);
                    connection.setRequestProperty("", "");

                    Log.d("Result_", "33333 POST");
                    connection.connect();
                    Log.d("Result_", "44444 POST");
                    InputStream is = connection.getInputStream();
                    Log.d("Result_", "Response: " + is);
                    int i;
                    String s = "";
                    while ((i = is.read()) != -1) {

                        // converts integer to character
                        char c = (char) i;

                        // prints character
                        s += c;
                        Log.d("Result_", "Response: " + c);
                    }
                    Log.d("Result_", "Response: " + s);


                } catch (Exception e) {
                    Log.d("Result_", "Error: " + e);
                }
            }
        });


//        HttpURLConnection connection = null;
//        Toast.makeText(context,"createRequest",Toast.LENGTH_SHORT).show();
//        try {
//            Log.d("Result_","11111");
//            connection = (HttpURLConnection) (new URL("https://api.androidhive.info/json/contacts.json")).openConnection();
//            Log.d("Result_","22222");
//            connection.setRequestMethod("GET"); // or post
//            Log.d("Result_","33333");
//            connection.connect();
//            Log.d("Result_","44444");
//            InputStream is = connection.getInputStream();
//            Log.d("Result_","Response: "+is.toString());
//        } catch (Exception e) {
//            Log.d("Result_","Error: "+e);
//        }
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        HttpURLConnection connection = null;

        try {
            Log.d("Result_", "11111");
            connection = (HttpURLConnection) (new URL("https://api.androidhive.info/json/contacts.json")).openConnection();
            Log.d("Result_", "22222");
            connection.setRequestMethod("GET"); // or post
            Log.d("Result_", "33333");
            connection.connect();
            Log.d("Result_", "44444");
            InputStream is = connection.getInputStream();
            Log.d("Result_", "Response: " + is.toString());
        } catch (Exception e) {
            Log.d("Result_", "Error: " + e);
        }

        return null;
    }
}
