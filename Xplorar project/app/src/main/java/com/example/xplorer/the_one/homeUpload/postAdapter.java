package com.example.xplorer.the_one.homeUpload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xplorer.R;
import com.example.xplorer.the_one.loginSignupPro.Database.UserHelperClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

public class postAdapter extends RecyclerView.Adapter<postAdapter.ViewHolder>{

    public Context mContext;
    public List<post> mPost;

    private FirebaseUser firebaseUser;

    public postAdapter(Context mContext, List<post> mPost) {
        this.mContext = mContext;
        this.mPost = mPost;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent,false);

        return new postAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        post post = mPost.get(position);

        Glide.with(mContext).load(post.getPostImage()).into(holder.post_image);

        if (post.getDescription().equals("")){

            holder.post_title.setVisibility(View.GONE);

        }else{
            holder.post_title.setVisibility(View.VISIBLE);
            holder.post_title.setText(post.getDescription());
        }

        publisherInfo(holder.post_title, holder.publisher, post.getPublisher());

    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }










    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView post_image,comment,rating;
        public TextView  publisher, comments, post_title;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            post_image = itemView.findViewById(R.id.post_image);
            comment = itemView.findViewById(R.id.comment);
            rating = itemView.findViewById(R.id.rating);
            publisher = itemView.findViewById(R.id.publisher);
            comments = itemView.findViewById(R.id.comments);
            post_title = itemView.findViewById(R.id.ImageName);

        }
    }

    private void publisherInfo(final TextView post_title,TextView publisher,final String imageName ){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Images").child(imageName);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uploadinfo uploadinfo = snapshot.getValue(uploadinfo.class);
//                Glide.with(mContext).load(uploadinfo.getImageURL()).into()
                post_title.setText(uploadinfo.getImageName());
                publisher.setText(uploadinfo.getImageName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
