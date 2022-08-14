package com.example.xplorer.the_one.loginSignupPro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xplorer.R;
import com.example.xplorer.the_one.welcome.welcome_screen;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class signup_screen extends AppCompatActivity {

    ImageView btnback;
    Button nxt, lgn;
    TextView signuptitletxt;
    TextInputEditText name, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        btnback = findViewById(R.id.sgnup_back_btn);
        signuptitletxt = findViewById(R.id.signup_title);
        nxt = findViewById(R.id.btn_next_signup);
        lgn = findViewById(R.id.btn_back_login);


        name = findViewById(R.id.enter_name);
        email = findViewById(R.id.enter_email);
        pass = findViewById(R.id.enter_password);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), welcome_screen.class);
                startActivity(i);
                finish();
            }
        });
//
//
//
//        nxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String Sname = name.getEditText().toString();
//                String Semail = email.getEditText().toString();
//                String Sphone = phone.getEditText().toString();
//                String Spass = pass.getEditText().toString();
//
//                if (!validateName() | !validateEmail() | !validatePhone() |!validatepass()){
//                    return;
//                } else {
//                    Intent i = new Intent(getApplicationContext(), signup_screen2.class);
//
//                    i.putExtra("Gname", Sname);
//                    i.putExtra("Gemail", Semail);
//                    i.putExtra("Gphone", Sphone);
//                    i.putExtra("Gpass", Spass);
//
//                    startActivity(i);
//                }
//            }
//        });
//
        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), login_screen.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void Nextcallsignup(View view) {


        if (!validateName() | !validateEmail() | !validatepass()) {
            return;
        }

        String _name = (name.getText()).toString();
        String _email = (email.getText()).toString();
        String _pass = (pass.getText()).toString();

        Intent in = new Intent(getApplicationContext(), signup_screen2.class);

        in.putExtra("S1name", _name);
        in.putExtra("S1email", _email);
        in.putExtra("S1password", _pass);


        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(btnback, "animatetobackbtn");
        pairs[1] = new Pair<View, String>(signuptitletxt, "animatetoNextTitletext");
        pairs[2] = new Pair<View, String>(nxt, "animatetoNextSignup");
        pairs[3] = new Pair<View, String>(lgn, "animatebacklogin");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signup_screen.this, pairs);
            startActivity(in, options.toBundle());
        } else {
            startActivity(in);
        }
    }

    private boolean validateName() {
        String s = name.getText().toString().trim();

        if (s.isEmpty()) {
            name.setError("Empty");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String s = email.getText().toString().trim();
        String mailCheck = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (s.isEmpty()) {
            email.setError("Empty");
            return false;
        } else if (!s.matches(mailCheck)) {
            email.setError("Enter Valid Email");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

//    private boolean validatePhone() {
//        String s = phone.getEditText().getText().toString().trim();
//
//        if (s.isEmpty()) {
//            phone.setError("Empty");
//            return false;
//        } else {
//            phone.setError(null);
//            phone.setErrorEnabled(false);
//            return true;
//        }
//    }

    private boolean validatepass() {
        String s = pass.getText().toString().trim();
        String passCheck = "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                ".{4,}";

        if (s.isEmpty()) {
            pass.setError("Empty");
            return false;
        } else if (!s.matches(passCheck)) {
            pass.setError("Enter Strong Password");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }

}