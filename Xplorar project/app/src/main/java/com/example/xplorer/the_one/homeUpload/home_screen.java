package com.example.xplorer.the_one.homeUpload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.xplorer.R;
import com.example.xplorer.the_one.homeUpload.Fragment.HomeFragment;
import com.example.xplorer.the_one.homeUpload.Fragment.ProfileFragment;
import com.example.xplorer.the_one.homeUpload.Fragment.UploadFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class home_screen extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selectFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectFragment = new HomeFragment();
                            break;

                        case  R.id.nav_profile:
//                            SharedPreferences.Editor editor =getSharedPreferences("PREFS",MODE_PRIVATE).edit();
//                            SessionManager sessionManager = getSupportFragmentManager("")
//                            editor.putString("phone", FirebaseAuth.getInstance().getCurrentUser().getUid());
//                            editor.apply();
                            selectFragment = null;
                            startActivity(new Intent(home_screen.this,profile_screen.class));
                            break;

                        case R.id.nav_upload:
                            selectFragment = null;
                            startActivity(new Intent(home_screen.this,upload_screen.class));
                            break;

                    }
                    if (selectFragment != null)
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectFragment).commit();
                    }

                    return true;
                }
            };
}