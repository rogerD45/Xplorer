package com.example.xplorer.the_one.homeUpload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xplorer.MainActivity;
import com.example.xplorer.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class current_loc extends AppCompatActivity {
    TextView textViewlatt, textViewlong, textViewadd, textViewloc, textViewcon;
    Button button;
    FusedLocationProviderClient fusedLocationProviderClient;

    public String latit,longi,address,locality,country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_loc);

        textViewlatt = findViewById(R.id.tv_latt);
        textViewlong = findViewById(R.id.tv_longi);
        textViewadd = findViewById(R.id.tv_addrs);
        textViewloc = findViewById(R.id.tv_local);
        textViewcon = findViewById(R.id.tv_con);

        button = findViewById(R.id.button);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(current_loc.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    showLocation();

                } else {
                    ActivityCompat.requestPermissions(current_loc.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);

                }
            }
        });
    }

    private void showLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(current_loc.this, Locale.getDefault());
                    try {
                        List<Address> addressesList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude()
                                , 1);

                        latit = "Latitude"+ addressesList.get(0).getLatitude();
                        longi = "Longitude"+ addressesList.get(0).getLongitude();
                        address = ""+addressesList.get(0).getAddressLine(0);
                        locality = ""+ addressesList.get(0).getLocality();
                        country = ""+ addressesList.get(0).getCountryName();

//                        textViewlatt.setText("Latitude" + addressesList.get(0).getLatitude());
//                        textViewlong.setText("Longitude" + addressesList.get(0).getLongitude());
//                        textViewadd.setText(addressesList.get(0).getAddressLine(0));
//                        textViewloc.setText(addressesList.get(0).getLocality());
//                        textViewcon.setText(addressesList.get(0).getCountryName());

                        textViewlatt.setText(latit);
                        textViewlong.setText(longi);
                        textViewadd.setText(address);
                        textViewloc.setText(locality);
                        textViewcon.setText(country);
                        String neww = textViewlatt.getText().toString().trim();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                } else {
                    Toast.makeText(current_loc.this, "Location null error", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}