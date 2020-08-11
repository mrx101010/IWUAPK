package com.example.iwuapk.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.iwuapk.R;
import com.example.iwuapk.intro.IntroActivity;

public class SplashScreen extends AppCompatActivity {

    private int waktu_loading = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, IntroActivity.class);
                startActivity(i);
                finish();
            }
        }, waktu_loading);
    }
}