package com.example.xplorer.the_one.homeUpload.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xplorer.R;
import com.example.xplorer.the_one.homeUpload.post;
import com.example.xplorer.the_one.homeUpload.postAdapter;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private postAdapter postAdapter;
    private List<post> postLists;

    private List<String> followingList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = view.findViewById(R.id.reycler_postImages);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        postLists = new ArrayList<>();
        postAdapter = new postAdapter(getContext(), postLists);
        recyclerView.setAdapter(postAdapter);

        return view;
    }

//    private void checkFollowing(){
//        followingList = new ArrayList<>();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("");
//    }


    private void readPost(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Images");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postLists.clear();
                for (DataSnapshot ss : snapshot.getChildren()){
                    post post = ss.getValue(post.class);

                    if (post.getPublisher().equals(post)) {
                        postLists.add(post);
                    }
//                    for (String id : followingList){
//                        if (post.getPostTitle().equals(id)){
//                            postLists.add(post);
//                        }
//                    }
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}