package com.example.sahaya;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Guard_Home_Fragment extends Fragment {
    AppCompatButton qrscan,outsider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_guard__home_, container, false);
        outsider=view.findViewById(R.id.outsider_guard_home);
        qrscan=view.findViewById(R.id.qrscan_guard_home);


        qrscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),QRScanner.class);
                startActivity(i);
            }
        });

        return view;
    }
}