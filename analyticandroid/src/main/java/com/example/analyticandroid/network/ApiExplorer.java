package com.example.analyticandroid.network;

import android.content.Context;
import android.util.Log;

import com.example.analyticandroid.IO.CacheBloc;
import com.example.analyticandroid.androidnetworking.AndroidNetworking;
import com.example.analyticandroid.androidnetworking.common.Priority;
import com.example.analyticandroid.androidnetworking.error.ANError;
import com.example.analyticandroid.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.analyticandroid.models.RequestModel;
import com.example.analyticandroid.utils.DataFlowController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiExplorer {

//    public static void createGETRequest(Context context, final OnDataLoaded onDataLoaded) {
//        ApiExplorer.execute(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Result_", "run...");
//                HttpURLConnection connection = null;
//                try {
//                    Log.d("Result_", "11111");
//                    connection = (HttpURLConnection) (new URL("https://api.androidhive.info/json/contacts.json")).openConnection();
//                    Log.d("Result_", "22222");
//                    connection.setRequestMethod("GET"); // or post
//                    Log.d("Result_", "33333");
//                    connection.connect();
//                    Log.d("Result_", "44444");
//                    InputStream is = connection.getInputStream();
//                    Log.d("Result_", "Response: " + is);
//                    int i;
//                    String s = "";
//                    while ((i = is.read()) != -1) {
//
//                        // converts integer to character
//                        char c = (char) i;
//
//                        // prints character
//                        s += c;
//                        Log.d("Result_", "Response: " + c);
//                    }
//                    Log.d("Result_", "Response: " + new JSONArray(s));
//                    final String finalS = s;
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                onDataLoaded.onDataLoadedSuccessfully(new JSONArray(finalS));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//
//                } catch (final Exception e) {
//                    Log.d("Result_", "Error: " + e);
//
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//                        @Override
//                        public void run() {
//                            onDataLoaded.onError(e);
//                        }
//                    });
//
//
//                }
//            }
//        });
//
//    }
//
//    public static void createPOSTRequest2(Context context, final OnDataLoaded onDataLoaded) {
//        ApiExplorer.execute(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection connection;
//                OutputStreamWriter request = null;
//
//                URL url = null;
//                String response = null;
//                String parameters = "userId=" + "1" + "&appId=" + "1";
//
//                try {
//                    Log.d("Result_", "11111");
//
//                    url = new URL("http://5.134.200.110:9090/Analytic_SDK_APIs/WS/SDK_WS/addSession");
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setDoOutput(true);
//                    connection.setRequestMethod("POST");
//                    connection.setRequestProperty("Content-Type", "application/json");
//
//                    Log.d("Result_", "222222");
//                    request = new OutputStreamWriter(connection.getOutputStream());
//                    request.write(parameters);
//
//                    Log.d("Result_", "encode: " + request.getEncoding());
//                    request.flush();
//                    request.close();
//                    String line = "";
//                    Log.d("Result_", "333333: " + connection.getErrorStream());
//                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
//                    BufferedReader reader = new BufferedReader(isr);
//                    StringBuilder sb = new StringBuilder();
//                    while ((line = reader.readLine()) != null) {
//                        sb.append(line + "\n");
//                    }
//                    // Response from server after login process will be stored in response variable.
//                    response = sb.toString();
//                    Log.d("Result_", "response : " + response);
//
//                    // You can perform UI operations here
//                    System.out.println("this response" + response);
////                    Toast.makeText(this,"Message from Server: \n"+ response, 0).show();
//                    isr.close();
//                    reader.close();
//
//                } catch (IOException e) {
//                    Log.d("Result_", "error: " + e);
//
//                    e.printStackTrace();
//                    // Error
//                }
//            }
//        });
//
//    }
//
//    public static void createPOSTRequest(Context context, final OnDataLoaded onDataLoaded) {
//        ApiExplorer.execute(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("Result_", "run...");
//                HttpURLConnection connection = null;
//                String text = "";
//                BufferedReader reader = null;
//                try {
//                    Log.d("Result_", "11111");
//                    // Create data variable for sent values to server
//
//                    String data = URLEncoder.encode("name", "UTF-8")
//                            + "=" + URLEncoder.encode("Bashir", "UTF-8");
//
//                    data += "&" + URLEncoder.encode("email", "UTF-8") + "="
//                            + URLEncoder.encode("bashir", "UTF-8");
//
//                    data += "&" + URLEncoder.encode("user", "UTF-8")
//                            + "=" + URLEncoder.encode("bashiraltereh", "UTF-8");
//
//                    data += "&" + URLEncoder.encode("pass", "UTF-8")
//                            + "=" + URLEncoder.encode("1234", "UTF-8");
//
//                    Log.d("Result_", "Body: " + data);
//                    connection = (HttpURLConnection) (new URL("https://api.androidhive.info/json/contacts.json")).openConnection();
//
//                    connection.setDoOutput(true);
//                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
//                    wr.write(data);
//                    wr.flush();
//                    Log.d("Result_", "22222");
//
//
//                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    StringBuilder sb = new StringBuilder();
//                    String line = null;
//
//                    // Read Server Response
//                    while ((line = reader.readLine()) != null) {
//                        // Append server response in string
//                        sb.append(line + "\n");
//                    }
//                    Log.d("Result_", "333333");
//
//
//                    text = sb.toString();
//                    Log.d("Result_", "response: " + text);
//
//                } catch (Exception ex) {
//                    Log.d("Result_", "error: " + ex);
//
//                } finally {
//                    try {
//
//                        reader.close();
//                    } catch (Exception ex) {
//
//
//                        Log.d("Result_", "file error: " + ex);
//                    }
////                    Log.d("Result_", "22222");
////                    connection.setRequestMethod("GET"); // or post
////                    connection.setRequestProperty("","");
////                    Log.d("Result_", "33333");
////                    connection.connect();
////                    Log.d("Result_", "44444");
////                    InputStream is = connection.getInputStream();
////                    Log.d("Result_", "Response: " + is);
////                    int i;
////                    String s = "";
////                    while ((i = is.read()) != -1) {
////
////                        // converts integer to character
////                        char c = (char) i;
////
////                        // prints character
////                        s += c;
////                        Log.d("Result_", "Response: " + c);
////                    }
////                    Log.d("Result_", "Response: " + new JSONArray(s));
////                    final String finalS = s;
////                    new Handler(Looper.getMainLooper()).post(new Runnable() {
////                        @Override
////                        public void run() {
////                            try {
////                                onDataLoaded.onDataLoadedSuccessfully(new JSONArray(finalS));
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
////
////                        }
////                    });
////
////                } catch (final Exception e) {
////                    Log.d("Result_", "Error: " + e);
////
////                    new Handler(Looper.getMainLooper()).post(new Runnable() {
////                        @Override
////                        public void run() {
////                            onDataLoaded.onError(e);
////                        }
////                    });
//
//
//                }
//            }
//        });
//
//    }


    public static void DataLoader(final Context context, final OnDataLoaded onDataLoaded, String url, final Map<String, String> header, final JSONObject body, final RequestPriority priority) {
        final Map<String, String> map = new HashMap<>();
        Log.d("Result_", "URL: " + url);

//        if (context != null)
//            DataFlowController.checkInternetSpeed(context);
        Log.d("Result_", "Priority" + priority.name());
        DataFlowController.requestQueue.add(new RequestModel(url, header, body, priority));
        Log.d("Result_", "Queue size: " + DataFlowController.requestQueue.size());
        AndroidNetworking.post(url)
                .addJSONObjectBody(body)
                .setTag("test")
                .addHeaders(header)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("Result_", "response: " + response);
                        onDataLoaded.onDataLoadedSuccessfully(response);
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("Result_", "onError: " + error);
                        JSONObject cacheJson = new JSONObject();
                        try {
                            cacheJson.put("url", WebServiceURL.AddSessionUrl());
                            cacheJson.put("body", body);
                            cacheJson.put("header", header.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (context != null) {
                            CacheBloc.saveToPreferences(context, CacheBloc.REQUEST_CACHE, cacheJson.toString());

                            String r = CacheBloc.readFromPreferences(context, CacheBloc.REQUEST_CACHE, "noooooo");
                            Log.d("Result_", "cache: " + r);
                        }
                        onDataLoaded.onError(error);
                        //TODO: save request body in cache and resend it when get connection again
                    }
                });
    }
}
