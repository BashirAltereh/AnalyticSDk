package com.example.analyticandroid.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Function {

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

    @SuppressLint("HardwareIds")
    public String openSession(Context context) {
        Log.d("Function", "openSession");

        PackageManager pm = context.getPackageManager();
        PackageInfo info = new PackageInfo();
        try {
            info = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Activity activity = (Activity) context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Log.d("UUID_", "UUID: " + UUID.randomUUID().toString());
        Log.d("SecureID_", "SecureID: " + Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID));


        Map<String, String> map = new HashMap<>();

        map.put("SystemVersion", android.os.Build.VERSION.RELEASE);
        map.put("DeviceModel", Build.DEVICE);
        map.put("uuid", UUID.randomUUID().toString());
        map.put("androidSecureId", Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID));
        map.put("Fingerprint", Build.FINGERPRINT);
        map.put("Package", this.getClass().getCanonicalName());
        map.put("BOARD", Build.BOARD);
        map.put("BRAND", Build.BRAND);
        map.put("DISPLAY", Build.DISPLAY);
        map.put("HARDWARE", Build.HARDWARE);
        map.put("HOST", Build.HOST);
        map.put("ID", Build.ID);
        map.put("MANUFACTURER", Build.MANUFACTURER);
        map.put("MODEL", Build.MODEL);
        map.put("PRODUCT", Build.PRODUCT);

        map.put("SERIAL", Build.SERIAL);
        map.put("TAGS", Build.TAGS);
        map.put("TYPE", Build.TYPE);
        map.put("APPNAME", info.applicationInfo.loadLabel(pm).toString());
        map.put("VERSION", info.versionName);
        map.put("BUILDNUMBER", String.valueOf(getLongVersionCode(info)));
        map.put("screenWidth", String.valueOf(width));
        map.put("screenHeight", String.valueOf(height));

        SYSTEMVERSION = map.get("SystemVersion");
        DEVICEMODEL = map.get("DeviceModel");
        UUID_APP = map.get("uuid");
        FINGERPRINT = map.get("Fingerprint");
        PACKAGE = map.get("Package");
        BOARD = map.get("BOARD");
        BRAND = map.get("BRAND");
        DISPLAY = map.get("DISPLAY");
        HARDWARE = map.get("HARDWARE");
        HOST = map.get("HOST");
        ID = map.get("ID");
        SECURE_ANDROID_ID = map.get("androidSecureId");
        MANUFACTURER = map.get("MANUFACTURER");
        MODEL = map.get("MODEL");
        PRODUCT = map.get("PRODUCT");
        SERIAL = map.get("SERIAL");
        TAGS = map.get("TAGS");
        TYPE = map.get("TYPE");
        APPNAME = map.get("APPNAME");
        VERSION = map.get("VERSION");
        BUILDNUMBER = map.get("BUILDNUMBER");
        SCREEN_WIDTH = map.get("screenWidth");
        SCREEN_HEIGHT = map.get("screenHeight");

        Log.d("Function", "Function: " + map);
        return map.toString();
    }

    private long getLongVersionCode(PackageInfo info) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return info.getLongVersionCode();
        }
        return info.versionCode;
    }

    public void closeSession() {
        Log.d("Function", "closeSession");
    }

    public void suspendSession() {
        Log.d("Function", "suspendSession");
    }

    public void collectUserData() {
        Log.d("Function", "collectUserData");
    }
}
