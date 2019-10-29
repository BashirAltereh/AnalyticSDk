package com.example.analyitcandroidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.analyticandroid.network.ApiExplorer;
import com.example.analyticandroid.utils.Function;
import com.example.analyticandroid.utils.LifeCycle;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
     TextView mTvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvInfo  = findViewById(R.id.tv_info);
        mTvInfo.setText((new Function()).openSession());
        ApiExplorer.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("Result_","run...");

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
                    Log.d("Result_","Response: "+is);
                    int i;
                    String s = "";
                    while((i = is.read())!=-1) {

                        // converts integer to character
                        char c = (char)i;

                        // prints character
                        s+=c;
                        Log.d("Result_","Response: "+c);
                    }
                        Log.d("Result_","Response: "+s);


                } catch (Exception e) {
                    Log.d("Result_","Error: "+e);
                }
            }
        });
    }
}
