package com.example.xplorer.the_one.homeUpload.New;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xplorer.R;
import com.example.xplorer.the_one.homeUpload.home_screen;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;


import java.io.File;
import java.util.HashMap;

public class post_Activity extends AppCompatActivity {
//
//    Uri imageUri;
//    String myUri = "";
//    StorageTask uploadTask;
//    StorageReference storageReference;
//
//    ImageView close,image_added;
//    TextView post;
//    EditText description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

//        close = findViewById(R.id.close);
//        image_added = findViewById(R.id.image_added);
//        post = findViewById(R.id.upload);
//        description = findViewById(R.id.description);
//
//
//
//        storageReference = FirebaseStorage.getInstance().getReference("posts");
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(post_Activity.this, home_screen.class));
//                finish();
//            }
//        });
//
//        post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadImage();
//
//            }
//        });
//
////        UCrop.of(imageUri,image_added)
////                .withAspectRatio(1, 1)
////                .start(post_Activity.this);
//
//
//
//    }
//
//    private String getFileExtension(Uri uri){
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//
//    private void uploadImage(){
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Posting");
//        progressDialog.show();
//
//        if (imageUri !=null){
//            StorageReference filerefrence = storageReference.child(System.currentTimeMillis()
//            + "." + getFileExtension(imageUri));
//
//            uploadTask = filerefrence.putFile(imageUri);
//            uploadTask.continueWithTask(new Continuation() {
//                @Override
//                public Object then(@NonNull Task task) throws Exception {
//                    if (!task.isComplete()){
//                        throw task.getException();
//                    }
//
//                    return filerefrence.getDownloadUrl();
//
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    if (task.isSuccessful()){
//
//                        Uri downloadUri = task.getResult();
//                        myUri = downloadUri.toString();
//
//                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("posts");
//
//                        String postid = reference.push().getKey();
//
//                        HashMap<String,Object> hashMap = new HashMap<>();
//                        hashMap.put("postid",postid);
//                        hashMap.put("postimage",myUri);
//                        hashMap.put("description",description.getText().toString());
//                        hashMap.put("publisher", FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//                        reference.child(postid).setValue(hashMap);
//
//                        progressDialog.dismiss();
//
//                        startActivity(new Intent(post_Activity.this,home_screen.class));
//                        finish();
//                    }else{
//                        Toast.makeText(post_Activity.this, "Failed!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(post_Activity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }else{
//            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//            if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
//                imageUri = UCrop.getOutput(data);
//                image_added.setImageURI(imageUri);
//            } else  {
//                Toast.makeText(this, "Something gone Wrong!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(post_Activity.this,home_screen.class));
//                finish();
//            }
//        }
    }

}