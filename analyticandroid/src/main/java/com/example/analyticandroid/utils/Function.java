package com.example.analyticandroid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import com.example.analyticandroid.Tasks;
import com.example.analyticandroid.androidnetworking.error.ANError;
import com.example.analyticandroid.network.ApiExplorer;
import com.example.analyticandroid.network.OnDataLoaded;
import com.example.analyticandroid.network.RequestPriority;
import com.example.analyticandroid.network.WebServiceParams;
import com.example.analyticandroid.network.WebServiceURL;
import com.example.analyticandroid.services.MyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

/**
 * Created by BashirAltereh on 10/31/2019.
 */

public class Function implements OnDataLoaded {
    public static String SYSTEMVERSION;
    public static String DEVICEMODEL;
    public static String FINGERPRINT;
    public static String PACKAGE;
    public static String BOARD;
    public static String BRAND;
    public static String DISPLAY;
    public static String HARDWARE;
    public static String HOST;
    public static String ID;
    public static String MANUFACTURER;
    public static String MODEL;
    public static String SERIAL;
    public static String TAGS;
    public static String PRODUCT;
    public static String TYPE;
    public static String APPNAME;
    public static String VERSION;
    public static String BUILDNUMBER;
    public static String UUID_APP;
    public static String SECURE_ANDROID_ID;
    public static String SCREEN_WIDTH;
    public static String SCREEN_HEIGHT;

    public void openSession(Context context) {
        Log.d("Function", "openSession");
        Intent i = new Intent(context, MyService.class);
        context.startService(i);
        JSONObject attributes = getDeviceInfo(context);

        Log.d("Function", "Function: " + attributes);

        JSONObject object = readJsonFile(context);
        String s = "";
        try {
            s = object.getString("appKey");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ApiExplorer.DataLoader(context, this, WebServiceURL.AddSessionUrl(), WebServiceParams.getHeader(), WebServiceParams.openSessionParams("", "bashir", 1, "damascus", 1, Integer.parseInt(s), attributes), RequestPriority.IMMEDIATE);

    }


    @SuppressLint("HardwareIds")
    private JSONObject getDeviceInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = new PackageInfo();
        try {
            info = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        Activity activity = (Activity) context;
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;

        Log.d("UUID_", "UUID: " + UUID.randomUUID().toString());
        Log.d("SecureID_", "SecureID: " + Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID));


        JSONObject attributes = new JSONObject();
        try {
            attributes.put("SystemVersion", Build.VERSION.RELEASE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("DeviceModel", Build.DEVICE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("uuid", UUID.randomUUID().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("androidSecureId", Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("Fingerprint", Build.FINGERPRINT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("Package", this.getClass().getCanonicalName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("BOARD", Build.BOARD);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("BRAND", Build.BRAND);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("DISPLAY", Build.DISPLAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("HARDWARE", Build.HARDWARE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("HOST", Build.HOST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("ID", Build.ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("Manufacturer", Build.MANUFACTURER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("Model", Build.MODEL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("Product", Build.PRODUCT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("Serial", Build.SERIAL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("TAGS", Build.TAGS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("TYPE", Build.TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("AppName", info.applicationInfo.loadLabel(pm).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("Version", info.versionName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            attributes.put("BuildNumber", String.valueOf(getLongVersionCode(info)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//            attributes.put("screenWidth", String.valueOf(width));
//            attributes.put("screenHeight", String.valueOf(height));

        try {
            SYSTEMVERSION = attributes.getString("SystemVersion");
            DEVICEMODEL = attributes.getString("DeviceModel");
            UUID_APP = attributes.getString("uuid");
            FINGERPRINT = attributes.getString("Fingerprint");
            PACKAGE = attributes.getString("Package");
            BOARD = attributes.getString("BOARD");
            BRAND = attributes.getString("BRAND");
            DISPLAY = attributes.getString("DISPLAY");
            HARDWARE = attributes.getString("HARDWARE");
            HOST = attributes.getString("HOST");
            ID = attributes.getString("ID");
            SECURE_ANDROID_ID = attributes.getString("androidSecureId");
            MANUFACTURER = attributes.getString("MANUFACTURER");
            MODEL = attributes.getString("MODEL");
            PRODUCT = attributes.getString("PRODUCT");
            SERIAL = attributes.getString("SERIAL");
            TAGS = attributes.getString("TAGS");
            TYPE = attributes.getString("TYPE");
            APPNAME = attributes.getString("APPNAME");
            VERSION = attributes.getString("VERSION");
            BUILDNUMBER = attributes.getString("BUILDNUMBER");
            SCREEN_WIDTH = attributes.getString("screenWidth");
            SCREEN_HEIGHT = attributes.getString("screenHeight");
        } catch (Exception e) {
        }
        return attributes;
    }


    private long getLongVersionCode(PackageInfo info) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return info.getLongVersionCode();
        }
        return info.versionCode;
    }

    public void closeSession(Context context) {
        Log.d("Function", "closeSession");
        ApiExplorer.DataLoader(context, this, WebServiceURL.CloseSession(), WebServiceParams.getHeader(), WebServiceParams.suspendAndCloseSessionParams("456789"), RequestPriority.IMMEDIATE);

    }

    public void suspendSession(Context context) {
        ApiExplorer.DataLoader(context, this, WebServiceURL.SuspendSession(), WebServiceParams.getHeader(), WebServiceParams.suspendAndCloseSessionParams("456789"), RequestPriority.IMMEDIATE);
    }


    @Override
    public void onDataLoadedSuccessfully(JSONObject jsonObject) {

    }

    @Override
    public void onDataLoadedWithError(ANError e, String url, Map<String, String> header, JSONObject body, RequestPriority priority) {
        Tasks.addRequestToQueue(url, header, body, priority);

    }


    private JSONObject readJsonFile(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("sdk_conf.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Log.d("jsonFile", "json: " + json);
            try {
                return new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
                return new JSONObject();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return new JSONObject();
        }
    }
}
