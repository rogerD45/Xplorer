package com.example.xplorer.the_one.homeUpload.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xplorer.R;
import com.example.xplorer.the_one.homeUpload.SessionManager;


public class ProfileFragment extends Fragment {

    TextView tv1,tv2,tv3,tv4,tv5,tv6;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        tv1 = view.findViewById(R.id.t1);
        tv2 = view.findViewById(R.id.t2);
        tv3 = view.findViewById(R.id.t3);
        tv4 = view.findViewById(R.id.t4);
        tv5 = view.findViewById(R.id.t5);
        tv6 = view.findViewById(R.id.t6);



        return view;
    }
}