package com.example.sahaya;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;


public class Admin_home_Fragment extends Fragment {
    ImageSlider imageSlider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_admin_home_, container, false);
        imageSlider=view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList=new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.zero,null));
        imageList.add(new SlideModel(R.drawable.one,null));
        imageList.add(new SlideModel(R.drawable.two,null));
        imageList.add(new SlideModel(R.drawable.three,null));
        imageSlider.setImageList(imageList);

        TextView Tv=view.findViewById(R.id.generaltext1);
        Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), General_group.class);
                Toast.makeText(getActivity(),"General Group",Toast.LENGTH_SHORT).show();
               startActivity(intent);
            }
        });

        return view;

    }
}