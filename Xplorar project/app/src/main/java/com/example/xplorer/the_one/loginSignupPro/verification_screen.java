package com.example.xplorer.the_one.loginSignupPro;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.xplorer.R;
import com.example.xplorer.the_one.homeUpload.home_screen;
import com.example.xplorer.the_one.homeUpload.upload_screen;
import com.example.xplorer.the_one.loginSignupPro.Database.UserHelperClass;
import com.example.xplorer.the_one.welcome.welcome_screen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;


public class verification_screen extends AppCompatActivity {


    String backendOtp;

    ImageView taskend;

    TextView NumberRecieve;

    EditText input1, input2, input3, input4, input5, input6;

    Button verify, resend;

    ProgressBar progress;

    String name,email,gender,dob,pass,phone;


//    PinView pinFromUser;
//    String codeBySystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_screen);

        name = getIntent().getStringExtra("User_Name");
        email = getIntent().getStringExtra("User_Email");
        gender = getIntent().getStringExtra("User_Gender");
        dob = getIntent().getStringExtra("User_DOB");
        pass = getIntent().getStringExtra("User_password");
        phone = getIntent().getStringExtra("User_Phone");


        verify = findViewById(R.id.verify_code);


        taskend = findViewById(R.id.end_task);
        NumberRecieve = findViewById(R.id.userphoneNumber);

        progress = findViewById(R.id.bar_progress);
//        name = findViewById(R.id.userName);
//        email = findViewById(R.id.userEmail);
//        gender = findViewById(R.id.usergender);
//        dob = findViewById(R.id.userdob);


        input1 = findViewById(R.id.otpinput1);
        input2 = findViewById(R.id.otpinput2);
        input3 = findViewById(R.id.otpinput3);
        input4 = findViewById(R.id.otpinput4);
        input5 = findViewById(R.id.otpinput5);
        input6 = findViewById(R.id.otpinput6);

// example
//        String str = getIntent().getStringExtra("User_Phone");
//        name.setText((getIntent().getStringExtra("User_Name")));
// example
//        name.setText((getIntent().getStringExtra("User_Name")));
//        email.setText((getIntent().getStringExtra("User_Name")));
//        gender.setText((getIntent().getStringExtra("User_Name")));
//        dob.setText((getIntent().getStringExtra("User_Name")));

        NumberRecieve.setText(phone);
        backendOtp = getIntent().getStringExtra("backOtp");

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!input1.getText().toString().trim().isEmpty() && !input2.getText().toString().trim().isEmpty()
                        && !input3.getText().toString().trim().isEmpty() && !input4.getText().toString().trim().isEmpty()
                        && !input5.getText().toString().trim().isEmpty() && !input6.getText().toString().trim().isEmpty()) {

                    String codeEnterOtp = input1.getText().toString() +
                            input2.getText().toString() +
                            input3.getText().toString() +
                            input4.getText().toString() +
                            input5.getText().toString() +
                            input6.getText().toString();

                    if (backendOtp != null) {
                        progress.setVisibility(View.VISIBLE);
                        verify.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                backendOtp, codeEnterOtp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(task -> {
                                    progress.setVisibility(View.GONE);
                                    verify.setVisibility(View.VISIBLE);

                                    if (task.isSuccessful()) {

                                        storeNewUsersData();

                                        Intent intent = new Intent(getApplicationContext(), login_screen.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(verification_screen.this, "Enter valid Code", Toast.LENGTH_SHORT).show();
                                    }
                                });


                    } else {
                        Toast.makeText(verification_screen.this, "Check the Internet Connection", Toast.LENGTH_SHORT).show();
                    }


                } else {

                    Toast.makeText(verification_screen.this, "Provide Full Code", Toast.LENGTH_SHORT).show();

                }
            }
        });

        move_otp();

        resend = findViewById(R.id.resend_code);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        ""+getIntent().getStringExtra("User_Phone"),
                        60,
                        TimeUnit.SECONDS,
                        verification_screen.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(verification_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newbackOtp, forceResendingToken);

                                backendOtp = newbackOtp;
                                Toast.makeText(verification_screen.this, "Resend Successful", Toast.LENGTH_SHORT).show();


                            }
                        }
                );

            }
        });

        taskend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), welcome_screen.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void storeNewUsersData() {

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("users");

        UserHelperClass addNewUser = new UserHelperClass(name,email,gender,dob,pass,phone);

        reference.child(phone).setValue(addNewUser);

    }


    private void move_otp() {
        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    input2.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    input3.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    input4.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    input5.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().trim().isEmpty()) {
                    input6.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        pinFromUser = findViewById(R.id.pin_view);
//
//        String _phoneNo = getIntent().getStringExtra("User_phone");
//
//        sendVerificationCodeToUser(_phoneNo);
//
//
//        String fullName = getIntent().getStringExtra("User_Name");
//        String email = getIntent().getStringExtra("User_Email");
//        String password = getIntent().getStringExtra("User_password");
//        String date = getIntent().getStringExtra("User_DOB");
//        String gender = getIntent().getStringExtra("User_Gender");
////        String phoneNo = getIntent().getStringExtra("User_phone");
//
//
//    }
//
//    private void sendVerificationCodeToUser(String phoneNo) {
//
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phoneNo,
//                60,
//                TimeUnit.SECONDS,
//                (Activity) TaskExecutors.MAIN_THREAD,
//                mCallbacks
//        );
//    }
//
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
//            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//                @Override
//                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                    super.onCodeSent(s, forceResendingToken);
//                    codeBySystem = s;
//                }
//
//                @Override
//                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                    String code = phoneAuthCredential.getSmsCode();
//                    if (code != null) {
//                        pinFromUser.setText(code);
//                        verifyCode(code);
//                    }
//                }
//
//                @Override
//                public void onVerificationFailed(@NonNull FirebaseException e) {
//                    Toast.makeText(verification_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            };
//
//    private void verifyCode(String code) {
//
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
//        signInWithPhoneAuthCredential(credential);
//
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//                            Toast.makeText(verification_screen.this, "Successful", Toast.LENGTH_SHORT).show();
//
//                        } else {
//
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//
//                                Toast.makeText(verification_screen.this, "Error occur!", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }
//                });
//    }
//
//    public void callNextScreenFromOTP(View view) {
//        String code = pinFromUser.getText().toString();
//        if (code.isEmpty()) {
//            verifyCode(code);
//        }
//    }
    }


}