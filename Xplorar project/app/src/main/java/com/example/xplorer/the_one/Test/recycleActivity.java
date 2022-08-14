package com.example.xplorer.the_one.Test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xplorer.R;
import com.example.xplorer.the_one.homeUpload.profile_screen;
import com.example.xplorer.the_one.homeUpload.upload_screen;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class recycleActivity extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;

    ImageView home,profile,upload;
    ImageView Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        home = findViewById(R.id.home);
        profile = findViewById(R.id.profile);
        upload = findViewById(R.id.upload);



        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Images"), model.class)
                        .build();




        FirebaseRecyclerAdapter<model,myadapter.myviewholder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<model, myadapter.myviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myadapter.myviewholder holder, int position, @NonNull model model) {



                FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                final String userid= firebaseUser.getUid();
                final String postkey=getRef(position).getKey();

                holder.comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getApplicationContext(),CommentsActivity.class);
                        intent.putExtra("postkey",postkey);
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public myadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };







        adapter = new myadapter(options);
        Log.d("check image data",options.toString());
        recview.setAdapter(adapter);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),recycleActivity.class);
                startActivity(i);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), profile_screen.class);
                startActivity(i);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), upload_screen.class);
                startActivity(i);
            }
        });




    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.startListening();
    }
}