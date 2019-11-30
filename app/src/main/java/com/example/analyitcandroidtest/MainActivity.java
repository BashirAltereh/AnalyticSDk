package com.example.analyitcandroidtest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * For testing
 * */

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    private TextView mTvInfo;
    private TextView mTvTraffic;
    ArrayList<Pair<String, String>> infoList;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        infoList = new ArrayList<>();
//        mTvInfo = findViewById(R.id.tv_info);
//        mTvTraffic = findViewById(R.id.tv_traffic);
//        String imei = "";
//        imei = getImei();
//        Log.d("imei_", "imei: " + imei);
//        infoList.add(new Pair<>("Appname: ", Function.APPNAME));
//        infoList.add(new Pair<>("Packge: ", Function.PACKAGE));
//        infoList.add(new Pair<>("IMEI: ", imei));
//        infoList.add(new Pair<>("UUID: ", Function.UUID_APP));
//        infoList.add(new Pair<>("Secure Android ID: ", Function.SECURE_ANDROID_ID));
//        infoList.add(new Pair<>("Manufacturer: ", Function.MANUFACTURER));
//        infoList.add(new Pair<>("Serial: ", Function.SERIAL));
//        infoList.add(new Pair<>("Version: ", Function.VERSION));
//        infoList.add(new Pair<>("Board: ", Function.BOARD));
//        infoList.add(new Pair<>("Brand: ", Function.BRAND));
//        infoList.add(new Pair<>("Build Number: ", Function.BUILDNUMBER));
//        infoList.add(new Pair<>("Device Model: ", Function.DEVICEMODEL));
//        infoList.add(new Pair<>("Finger print: ", Function.FINGERPRINT));
//        infoList.add(new Pair<>("Display: ", Function.DISPLAY));
//        infoList.add(new Pair<>("Hardware: ", Function.HARDWARE));
//        infoList.add(new Pair<>("Host ", Function.HOST));
//        infoList.add(new Pair<>("ID: ", Function.ID));
//        infoList.add(new Pair<>("Product: ", Function.PRODUCT));
//        infoList.add(new Pair<>("Model: ", Function.MODEL));
//        infoList.add(new Pair<>("Type: ", Function.TYPE));
//        infoList.add(new Pair<>("Tags: ", Function.TAGS));
//        infoList.add(new Pair<>("System version: ", Function.SYSTEMVERSION));
//        infoList.add(new Pair<>("Screen Width: ", Function.SCREEN_WIDTH));
//        infoList.add(new Pair<>("Screen Height: ", Function.SCREEN_HEIGHT));
//
//        StringBuilder sInfo = new StringBuilder();
//        for (int i = 0; i < infoList.size(); i++)
//            sInfo.append(infoList.get(i).first).append(infoList.get(i).second).append("\n\n");
//        mTvInfo.setText(sInfo.toString());
//
////        DataFlowController.trafficStats(this,this);
//        mTvTraffic.setOnClickListener(this);
////        readJSonFile();

        int uiMode = getResources().getConfiguration().uiMode;
        Log.d("uiMode_","uiMode: "+(uiMode &Configuration.UI_MODE_TYPE_MASK)+ " , " + Configuration.UI_MODE_TYPE_NORMAL);
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this);
            addresses = geocoder.getFromLocation(33.525369, 36.23085, 10); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (addresses == null || addresses.isEmpty()) {
                // Mygeocoder is a class with a http request to google server, that replaces Geocoder, if not work
//                addresses = MyGeocoder.getFromLocation(latitude, longitude, 10);
                Log.d("Address_","ifffffffffffffffffffffffffffff");
            }
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
            Log.d("Address_","address: "+addresses + " , "+city + " , "+ state);
            Log.d("Address_","done");

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Address_","error: "+e);
        }



    }

    private String getImei() {
        TelephonyManager telephonyManager =
                (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (isPermissionGranted()) {
            String imei = telephonyManager.getImei();
            if (!imei.isEmpty()) {
                Log.d("imei_", "123456");
//                mTvIMEI.setText("IMEI: " + imei);
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

//    @Override
//    public void onDataLoadedSuccessfully(JSONObject jsonArray) {
//        Toast.makeText(getApplicationContext(), "onDataLoadedSuccessfully", Toast.LENGTH_SHORT).show();
//
//        Log.d("Result_", "onDataLoadedSuccessfully");
//    }
//
//    @Override
//    public void onError(ANError e) {
//
//        Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
//        Log.d("Result_", "onError: " + e);
//
//    }
//
//    @Override
//    public void OnDataTraffic(long rxBytes, long txBytes) {
//        mTvTraffic.setText("rxBytes: "+rxBytes+" KB/s , txBytes"+txBytes+" KB/s");
//    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("name","Bashri");
        startActivity(intent);

    }

    @Override
    protected void onStop() {

        Log.d("Function", "onStop- ");
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        Log.d("Function", "suspendSession: " + isScreenOn);
        super.onStop();
    }
}
