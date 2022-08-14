package com.example.xplorer.the_one.homeUpload;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/////
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xplorer.the_one.Test.recycleActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/////


import android.os.Bundle;

import com.example.xplorer.R;


public class upload_screen extends AppCompatActivity {

    Button btnbrowse, btnupload;

    EditText txtdata;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog;


    TextView textViewlatt, textViewlong, textViewadd, textViewloc, textViewcon;
    Button button;
    FusedLocationProviderClient fusedLocationProviderClient;

    public String latit, longi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_screen);

        storageReference = FirebaseStorage.getInstance().getReference("Images");
        databaseReference = FirebaseDatabase.getInstance().getReference("Images");
        btnbrowse = (Button) findViewById(R.id.addImage);
        btnupload = (Button) findViewById(R.id.upload);
        txtdata = (EditText) findViewById(R.id.addDetails);
        imgview = (ImageView) findViewById(R.id.image);
        progressDialog = new ProgressDialog(upload_screen.this);


        textViewlatt = findViewById(R.id.tv_latt);
        textViewlong = findViewById(R.id.tv_longi);
//        textViewadd = findViewById(R.id.tv_addrs);
//        textViewloc = findViewById(R.id.tv_local);
//        textViewcon = findViewById(R.id.tv_con);

        button = findViewById(R.id.button);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(upload_screen.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    showLocation();

                } else {
                    ActivityCompat.requestPermissions(upload_screen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);

                }
            }
        });


        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UploadImage();

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
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
                    Geocoder geocoder = new Geocoder(upload_screen.this, Locale.getDefault());
                    try {
                        List<Address> addressesList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude()
                                , 1);

                        latit = "" + addressesList.get(0).getLatitude();
                        longi = "" + addressesList.get(0).getLongitude();
//                        address = ""+addressesList.get(0).getAddressLine(0);
//                        locality = ""+ addressesList.get(0).getLocality();
//                        country = ""+ addressesList.get(0).getCountryName();

//                        textViewlatt.setText("Latitude" + addressesList.get(0).getLatitude());
//                        textViewlong.setText("Longitude" + addressesList.get(0).getLongitude());
//                        textViewadd.setText(addressesList.get(0).getAddressLine(0));
//                        textViewloc.setText(addressesList.get(0).getLocality());
//                        textViewcon.setText(addressesList.get(0).getCountryName());

                        textViewlatt.setText(latit);
                        textViewlong.setText(longi);
//                        textViewadd.setText(address);
//                        textViewloc.setText(locality);
//                        textViewcon.setText(country);

                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                } else {
                    Toast.makeText(upload_screen.this, "Location null error", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));

    }


    public void UploadImage() {


        if (FilePathUri != null) {

            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();
                            String TempImageName = txtdata.getText().toString().trim();
                            String imageLatit = textViewlatt.getText().toString().trim();
                            String imageLongi = textViewlong.getText().toString().trim();


                            downloadUri.addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String fileLink = task.getResult().toString();

                                    Log.d("imageUrl",fileLink);

                                    Toast.makeText(upload_screen.this, fileLink, Toast.LENGTH_SHORT).show();

                            uploadinfo imageUploadInfo = new uploadinfo(TempImageName, imageLatit, imageLongi,fileLink.toString());
                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                                }
                            });



                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Upload Successfull", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            Intent i = new Intent(getApplicationContext(), recycleActivity.class);
                            startActivity(i);
                        }
                    });
        } else {

            Toast.makeText(upload_screen.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }


}