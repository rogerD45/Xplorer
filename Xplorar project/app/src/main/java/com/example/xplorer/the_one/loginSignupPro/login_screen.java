package com.example.xplorer.the_one.loginSignupPro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.xplorer.R;
import com.example.xplorer.the_one.Test.recycleActivity;
import com.example.xplorer.the_one.homeUpload.New.post_Activity;
import com.example.xplorer.the_one.homeUpload.SessionManager;
import com.example.xplorer.the_one.homeUpload.home_screen;
import com.example.xplorer.the_one.homeUpload.profile_screen;
import com.example.xplorer.the_one.welcome.welcome_screen;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class login_screen extends AppCompatActivity {

    TextInputEditText phoneNumber, password;

    ProgressBar progressBar;

    Button forget_P, signup, loginBtn;

    CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        rememberMe = findViewById(R.id.rememberMe);

        phoneNumber = findViewById(R.id.Login_phone);
        password = findViewById(R.id.Login_password);
        progressBar = findViewById(R.id.Login_progress);

        forget_P = findViewById(R.id.forget_pass);
        signup = findViewById(R.id.newAccount);
        loginBtn = findViewById(R.id.btn_login);


        SessionManager sessionManager =
                new SessionManager(login_screen.this, SessionManager.SESSION_REMEMBERME);

        if (sessionManager.checkRememberMe()) {
            HashMap<String, String> rememberMeDetails = sessionManager.getRememberMeDetailsFromSession();
            phoneNumber.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPHONE));
            password.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPASSWORD));
        }


        forget_P.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), new_password.class);
            startActivity(i);
        });

        signup.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), signup_screen.class);
            startActivity(i);
            finish();
        });




    }

    public void letUserLoggedIn(View view) {

        if (!isConected(this)) {
            showCustomDialog();
        }


        if (!validateFields()) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        loginBtn.setVisibility(View.GONE);

        //getting Data
        String _phoneNumber = phoneNumber.getText().toString().trim();
        String _password = password.getText().toString().trim();

        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        }

        final String _completePhoneNumber = _phoneNumber;

        if (rememberMe.isChecked()) {

            SessionManager sessionManager =
                    new SessionManager(login_screen.this, SessionManager.SESSION_REMEMBERME);

            SessionManager.createRememberMeSession(_phoneNumber, _password);

        }


        //Query for Database
        Query checkUser = FirebaseDatabase.getInstance().getReference("users").orderByChild("phone").equalTo(_completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {


                    phoneNumber.setError(null);
//                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = snapshot.child(_completePhoneNumber).child("pass").getValue(String.class);
                    if (systemPassword.equals(_password)) {
                        password.setError(null);
//                        password.setErrorEnabled(false);

                        String _fullname = snapshot.child(_completePhoneNumber).child("name").getValue(String.class);
                        String _email = snapshot.child(_completePhoneNumber).child("email").getValue(String.class);
                        String _gender = snapshot.child(_completePhoneNumber).child("gender").getValue(String.class);
                        String _dob = snapshot.child(_completePhoneNumber).child("dob").getValue(String.class);
                        String _phone = snapshot.child(_completePhoneNumber).child("phone").getValue(String.class);
                        String _password = snapshot.child(_completePhoneNumber).child("pass").getValue(String.class);

                        SessionManager sessionManager = new SessionManager(login_screen.this, SessionManager.SESSION_USERSESSION);
                        SessionManager.createLoginSession(_fullname, _email, _gender, _dob, _phone, _password);

                        Intent i = new Intent(getApplicationContext(), recycleActivity.class);
                        startActivity(i);
                        finish();

                        Toast.makeText(login_screen.this, _fullname + "\n" + _email + "\n" + _gender + "\n" + _dob + "\n" + _phone,
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        loginBtn.setVisibility(View.VISIBLE);


                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(login_screen.this, "Password Not Match!", Toast.LENGTH_SHORT).show();
                        loginBtn.setVisibility(View.VISIBLE);
                    }


                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(login_screen.this, "User Does Not Exists!", Toast.LENGTH_SHORT).show();
                    loginBtn.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(login_screen.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loginBtn.setVisibility(View.VISIBLE);
            }
        });
    }

    //checking internet connection

    private boolean isConected(login_screen login_screen) {

        ConnectivityManager connectivityManager = (ConnectivityManager) login_screen.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiCon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo MobileCon = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiCon != null && wifiCon.isConnected()) || (MobileCon != null && MobileCon.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(login_screen.this);
        builder.setMessage("Please Connect To Internet")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), welcome_screen.class));
                        finish();
                    }
                });
    }

    //velidation
    private boolean validateFields() {

        String _phonenumber = phoneNumber.getText().toString().trim();
        String _password = password.getText().toString().trim();

        if (_phonenumber.isEmpty()) {
            phoneNumber.setError("Empty!");
            phoneNumber.requestFocus();
            return false;
        } else if (_password.isEmpty()) {
            password.setError("Password is empty!");
            password.requestFocus();
            return false;
        } else {
            return true;
        }

    }


}