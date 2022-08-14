package com.example.xplorer.the_one.welcome;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.xplorer.R;
import com.example.xplorer.the_one.loginSignupPro.login_screen;
import com.example.xplorer.the_one.loginSignupPro.signup_screen;

public class welcome_screen extends AppCompatActivity {
    Button Glogin, Gsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        Glogin = findViewById(R.id.goLogin);
        Gsignup = findViewById(R.id.goSignup);

//        Glogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(welcome_screen.this, login_screen.class);
//                startActivity(i);
//            }
//        });
//        Gsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(welcome_screen.this, signup_screen.class);
//                startActivity(i);
//            }
//        });
    }


    public void animatetologin(View view) {
        Intent in = new Intent(getApplicationContext(), login_screen.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.goLogin), "login_transition");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions opti = ActivityOptions.makeSceneTransitionAnimation(welcome_screen.this, pairs);
            startActivity(in, opti.toBundle());
        } else {
            startActivity(in);
        }
    }

    public void animatetosignup(View view) {
        Intent in = new Intent(getApplicationContext(), signup_screen.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.goSignup), "signup_transition");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions opti = ActivityOptions.makeSceneTransitionAnimation(welcome_screen.this, pairs);
            startActivity(in, opti.toBundle());
        } else {
            startActivity(in);
        }
    }
}