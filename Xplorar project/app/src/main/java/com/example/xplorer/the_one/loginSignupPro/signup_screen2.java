package com.example.xplorer.the_one.loginSignupPro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xplorer.R;
import com.example.xplorer.the_one.welcome.welcome_screen;

import java.util.Calendar;

public class signup_screen2 extends AppCompatActivity {
    ImageView btnback;
    Button nxt, lgn;
    TextView signuptitletxt;

    RadioGroup R_group;
    RadioButton selectedGender;
    DatePicker Pick_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen2);

        btnback = findViewById(R.id.sgnup_back_btn);
        signuptitletxt = findViewById(R.id.signup_title);
        nxt = findViewById(R.id.btn_next_signup);
        lgn = findViewById(R.id.btn_back_login);


        R_group = findViewById(R.id.r_group);
        Pick_date = findViewById(R.id.age_picker);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), signup_screen.class);
                startActivity(i);
                finish();
            }
        });

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), login_screen.class);
                startActivity(i);
                finish();
            }
        });

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String _name = getIntent().getStringExtra("S1name");
                String _email = getIntent().getStringExtra("S1email");
                String _pass = getIntent().getStringExtra("S1password");


                if (!validateAge() | !validateGender()) {
                    return;
                }

                selectedGender = findViewById(R_group.getCheckedRadioButtonId());

                String _Gen = selectedGender.getText().toString();

                int day = Pick_date.getDayOfMonth();
                int month = Pick_date.getMonth();
                int year = Pick_date.getYear();

                String _date = day + "/" + month + "/" + year;

                Intent in = new Intent(getApplicationContext(), signup_screen3.class);

                in.putExtra("gender", _Gen);
                in.putExtra("dob", _date);
                in.putExtra("name", _name);
                in.putExtra("email", _email);
                in.putExtra("password", _pass);


                Pair[] pairs = new Pair[4];

                pairs[0] = new Pair<View, String>(btnback, "animatetobackbtn");
                pairs[1] = new Pair<View, String>(signuptitletxt, "animatetoNextTitletext");
                pairs[2] = new Pair<View, String>(nxt, "animatetoNextSignup");
                pairs[3] = new Pair<View, String>(lgn, "animatebacklogin");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signup_screen2.this, pairs);
                    startActivity(in, options.toBundle());
                } else {
                    startActivity(in);
                }

            }
        });
    }


    //    public void Nextcallsignup(View view) {
//
//        if (!validateAge() | !validateGender()) {
//            return;
//        }
//
//        selectedGender = findViewById(R_group.getCheckedRadioButtonId());
//
//        String _Gen = selectedGender.getText().toString();
//
//        int day = Pick_date.getDayOfMonth();
//        int month = Pick_date.getMonth();
//        int year = Pick_date.getYear();
//
//        String _date = day + "/" + month + "/" + year;
//
//        Intent in = new Intent(getApplicationContext(), signup_screen3.class);
//
//        in.putExtra("gender", _Gen);
//        in.putExtra("dob", _date);
//
//
//        Pair[] pairs = new Pair[4];
//
//        pairs[0] = new Pair<View, String>(btnback, "animatetobackbtn");
//        pairs[1] = new Pair<View, String>(signuptitletxt, "animatetoNextTitletext");
//        pairs[2] = new Pair<View, String>(nxt, "animatetoNextSignup");
//        pairs[3] = new Pair<View, String>(lgn, "animatebacklogin");
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signup_screen2.this, pairs);
//            startActivity(in, options.toBundle());
//        } else {
//            startActivity(in);
//        }
//    }
//
    private boolean validateGender() {
        if (R_group.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }


    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = Pick_date.getYear();
        int validAge = currentYear - userAge;

        if (validAge < 15) {
            Toast.makeText(this, "Required Age Must be more than 15", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


}