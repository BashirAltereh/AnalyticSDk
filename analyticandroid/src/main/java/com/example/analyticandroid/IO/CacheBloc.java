package com.example.analyticandroid.IO;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CacheBloc {

    public static final String REQUEST_CACHE = "RequestCache";


    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            return sharedPreferences.getString(preferenceName, defaultValue);
        }
        else {
            return "";
        }
    }


    public static void clearPreferences(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        prefs.edit().clear().apply();
    }
}
