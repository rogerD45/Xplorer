package com.example.xplorer.the_one.homeUpload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xplorer.R;
import com.example.xplorer.the_one.homeUpload.Fragment.ProfileFragment;
import com.example.xplorer.the_one.loginSignupPro.login_screen;

import java.util.HashMap;

public class profile_screen extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6;

    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        tv1 = findViewById(R.id.t1);
        tv2 = findViewById(R.id.t2);
        tv3 = findViewById(R.id.t3);
        tv4 = findViewById(R.id.t4);
        tv5 = findViewById(R.id.t5);
        tv6 = findViewById(R.id.t6);

        logout = findViewById(R.id.logout);

        SessionManager sessionManager = new SessionManager(this,SessionManager.SESSION_USERSESSION);
        HashMap<String,String> userDetails = sessionManager.getUserDetailsFromSession();

        String name = userDetails.get(SessionManager.KEY_FULLNAME);
        String email = userDetails.get(SessionManager.KEY_EMAIL);
        String gender = userDetails.get(SessionManager.KEY_GENDER);
        String dob = userDetails.get(SessionManager.KEY_DOB);
        String phone = userDetails.get(SessionManager.KEY_PHONE);
        String pass = userDetails.get(SessionManager.KEY_PASSWORD);

        tv1.setText(name);
        tv2.setText(email);
        tv3.setText(dob);
        tv4.setText(gender);
        tv5.setText(phone);
//        tv6.setText(pass);

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), login_screen.class);
//                startActivity(i);
//                onBackPressed();
//                finish();
//            }
//        });

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SharedPreferences myPrefs = getSharedPreferences("Activity",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                //AppState.getSingleInstance().setLoggingOut(true);
                setLoginState(true);

                Intent intent = new Intent(profile_screen.this,
                        login_screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


    }


    private void setLoginState(boolean status) {
        SharedPreferences sp = getSharedPreferences("LoginState",
                MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean("setLoggingOut", status);
        ed.commit();
    }

}