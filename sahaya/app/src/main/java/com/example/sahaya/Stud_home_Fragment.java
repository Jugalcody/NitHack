package com.example.sahaya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Stud_home_Fragment extends Fragment {

    ImageSlider imageSlider;
    String identity="";
SharedPreferences sp1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_studenr_home, container, false);
        imageSlider=view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList=new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.zero,null));
        imageList.add(new SlideModel(R.drawable.one,null));
        imageList.add(new SlideModel(R.drawable.two,null));
        imageList.add(new SlideModel(R.drawable.three,null));
        imageSlider.setImageList(imageList);

        TextView Tv=view.findViewById(R.id.generaltext);
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