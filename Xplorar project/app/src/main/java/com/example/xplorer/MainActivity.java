package com.example.xplorer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.xplorer.the_one.Test.recycleActivity;
import com.example.xplorer.the_one.homeUpload.current_loc;
import com.example.xplorer.the_one.homeUpload.home_screen;
import com.example.xplorer.the_one.homeUpload.upload_screen;
import com.example.xplorer.the_one.loginSignupPro.verification_screen;
import com.example.xplorer.the_one.welcome.welcome_screen;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView splash_S;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        splash_S = findViewById(R.id.explore_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), welcome_screen.class);
                startActivity(i);
                finish();
            }
        },4000);

    }
}