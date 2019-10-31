package com.example.analyitcandroidtest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.analyticandroid.network.ApiExplorer;
import com.example.analyticandroid.utils.Function;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView mTvInfo;
    TextView mTvIMEI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvInfo = findViewById(R.id.tv_info);
        mTvIMEI = findViewById(R.id.tv_imei);
        String imei = "";
        imei = getImei();
        Log.d("imei_", "imei: " + imei);
        mTvInfo.setText((new Function()).openSession());
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
                    Log.d("Result_", "Response: " + s);


                } catch (Exception e) {
                    Log.d("Result_", "Error: " + e);
                }
            }
        });
    }

    private String getImei() {
        TelephonyManager telephonyManager =
                (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (isPermissionGranted()) {
            String imei = telephonyManager.getImei();
            if (!imei.isEmpty()) {
                 Log.d("imei_","123456");
                mTvIMEI.setText("IMEI: " + imei);
            }
            return imei;
        }
        return "";
    }

    private boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        2);

                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 2: {
                Log.d("imei_", "onRequestPermissionsResult");
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("imei_", "onRequestPermissionsResult -- if");
                    Log.d("imei_", "imei: " + getImei());
                    //continue using `getImei()` or `getDeviceId()`

                } else {
                    Log.d("imei_", "onRequestPermissionsResult -- else");

                    getImei();

                    //Use device Id or use fallback case
                }
                return;
            }
        }
    }

}
