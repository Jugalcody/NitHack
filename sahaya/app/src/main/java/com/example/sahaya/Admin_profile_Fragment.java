package com.example.sahaya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Admin_profile_Fragment extends Fragment {

    AppCompatButton editAdmin;
    AppCompatButton editStudent;
    TextView name,identity,phone,college,address;
    String identity_="",ph="",pass="";
    ImageView imgP;
    SharedPreferences sp1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_admin_profile_, container, false);
        editStudent=view.findViewById(R.id.bt_student);
        name=view.findViewById(R.id.name1_admin_profile);
        identity=view.findViewById(R.id.identity_profile_admin);
        imgP=view.findViewById(R.id.image_profile_admin);
        phone=view.findViewById(R.id.phone_profile_admin);
        college=view.findViewById(R.id.college_profile_admin);
        address=view.findViewById(R.id.address_profile_admin);

        sp1=getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        name.setText(sp1.getString("name44",""));
        identity_=sp1.getString("identity","");
        ph=sp1.getString("phone","");


        Bitmap b = getbitmap(sp1.getString("bitmap",""));
        imgP.setImageBitmap(b);
        pass=sp1.getString("pass","");
        identity.setText(identity_);
        phone.setText(ph);
        college.setText(sp1.getString("college",""));
        address.setText(sp1.getString("address",""));
        editStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),edit_profile.class);
                startActivity(i);
            }
        });
        return view;
    }
    public Bitmap getbitmap(String s){
        byte[] bytes= Base64.decode(s,Base64.DEFAULT);
        Bitmap bitmap2= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        return bitmap2;
    }

}