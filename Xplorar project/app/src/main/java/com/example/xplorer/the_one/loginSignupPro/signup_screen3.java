package com.example.xplorer.the_one.loginSignupPro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xplorer.R;
import com.example.xplorer.the_one.welcome.welcome_screen;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class signup_screen3 extends AppCompatActivity {
    ScrollView scroll;
    ImageView btnback;
    Button nxt, lgn;
    TextView signuptitletxt;
    String countryCodePicker;
    EditText CountryCode;
    TextInputEditText phoneNumber;

    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String _name = (getIntent().getStringExtra("name"));
        String _email = (getIntent().getStringExtra("email"));
        String _gender = (getIntent().getStringExtra("gender"));
        String _dob = (getIntent().getStringExtra("dob"));
        String _pass = (getIntent().getStringExtra("password"));


        setContentView(R.layout.activity_signup_screen3);

        scroll = findViewById(R.id.scroll_v);
        phoneNumber = findViewById(R.id.enter_phone);
        btnback = findViewById(R.id.sgnup_back_btn);
        nxt = findViewById(R.id.btn_next_signup);
        lgn = findViewById(R.id.btn_back_login);
        signuptitletxt = findViewById(R.id.signup_title);
        CountryCode = findViewById(R.id.countryCode);

        progress = findViewById(R.id.bar_progress);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),signup_screen.class);
                startActivity(i);
                finish();
            }
        });



        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                if(!phoneNumber.getText().toString().trim().isEmpty()){
                    if((phoneNumber.getText().toString().trim()).length() <= 15){

                        progress.setVisibility(View.VISIBLE);
                        nxt.setVisibility(View.INVISIBLE);

                        String getnum = phoneNumber.getText().toString().trim();
                        countryCodePicker = CountryCode.getText().toString().trim();

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                ""+countryCodePicker+ getnum,
                                60,
                                TimeUnit.SECONDS,
                                signup_screen3.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        progress.setVisibility(View.GONE);
                                        nxt.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        progress.setVisibility(View.GONE);
                                        nxt.setVisibility(View.VISIBLE);
                                        Toast.makeText(signup_screen3.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(backOtp, forceResendingToken);
                                        progress.setVisibility(View.GONE);
                                        nxt.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), verification_screen.class);
                                        intent.putExtra("User_Phone",phoneNumber.getText().toString());
                                        intent.putExtra("User_Name", _name);
                                        intent.putExtra("User_Email", _email);
                                        intent.putExtra("User_Gender", _gender);
                                        intent.putExtra("User_DOB", _dob);
                                        intent.putExtra("User_password", _pass);
                                        intent.putExtra("backOtp",backOtp);

                                        startActivity(intent);
                                    }
                                }
                        );

                    } else {
                        Toast.makeText(signup_screen3.this, "Enter correct Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(signup_screen3.this, "Please provide Phone no.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

//    public void CallVerifyOTPScreen(View view){
//
//        if(!validatePhoneNumber()){
//            return;
//        }
//
//        String _name = (String.format(getIntent().getStringExtra("name")));
//        String _email = (String.format(getIntent().getStringExtra("email")));
//        String _gender = (String.format(getIntent().getStringExtra("gender")));
//        String _dob = (String.format(getIntent().getStringExtra("dob")));
//        String _pass = (String.format(getIntent().getStringExtra("password")));
//
//        String _getUserEnteredPhoneNumber = phoneNumber.getText().toString().trim();
//        String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;
//
//        Intent intent = new Intent(getApplicationContext(),verification_screen.class);
//
//        intent.putExtra("User_Name", _name);
//        intent.putExtra("User_Email", _email);
//        intent.putExtra("User_Gender", _gender);
//        intent.putExtra("User_DOB", _dob);
//        intent.putExtra("User_password", _pass);
//        intent.putExtra("User_phone", _phoneNo);
//
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View,String>(scroll,"animatetoNextSignup");
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signup_screen3.this, pairs);
//            startActivity(intent, options.toBundle());
//        } else {
//            startActivity(intent);
//        }
//
//
//    }

//    private boolean validatePhoneNumber() {
//
//        String s = phoneNumber.getText().toString().trim();
//
//        if (s.isEmpty()) {
//            phoneNumber.setError("Empty");
//            return false;
//        } else {
//            phoneNumber.setError(null);
//            return true;
//        }
//
//    }


}
