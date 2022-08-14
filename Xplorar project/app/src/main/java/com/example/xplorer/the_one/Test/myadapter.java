package com.example.xplorer.the_one.Test;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xplorer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {



    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }







    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {

        holder.n1.setText(model.getImageName());
//        holder.n2.setText(model.getImagelongitude());
////        holder.n3.setText(model.getImagelongitude());

        Glide.with(holder.img.getContext()).load(model.getImageURL().toString()).into(holder.img);



    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        ImageView img,comment;
        TextView n1,n2,n3;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img1);
            n1 = (TextView) itemView.findViewById(R.id.nametext);
            comment = (ImageView) itemView.findViewById(R.id.rate);
//            n2 = (TextView) itemView.findViewById(R.id.nametext2);
//            n3 = (TextView) itemView.findViewById(R.id.nametext3);

        }
    }


}
