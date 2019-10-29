package com.example.analyticandroid.utils;

import android.os.Build;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Function {
    public String openSession() {
        Log.d("Function", "openSession");
        String info = "Android " + android.os.Build.VERSION.RELEASE + " \n\nDevice Model*: " + Build.DEVICE +
                "\n\nFingerprint*:" + Build.FINGERPRINT + "\n\nPackage: " + this.getClass().getCanonicalName();
        Map<String, String> map = new HashMap<>();
        map.put("SystemVersion", android.os.Build.VERSION.RELEASE);
        map.put("DeviceModel", Build.DEVICE);
        map.put("Fingerprint", Build.FINGERPRINT);
        map.put("Package", this.getClass().getCanonicalName());
        Log.d("Function","Function: "+map);
        return map.toString();
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
