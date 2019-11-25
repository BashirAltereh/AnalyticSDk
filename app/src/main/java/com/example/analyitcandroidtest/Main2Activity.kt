package com.example.analyitcandroidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
/**
 * For testing
 * */
class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    }

    override fun onStop() {
        Log.d("ACTION_","onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("ACTION_","onDestroy")
        super.onDestroy()
    }
}
