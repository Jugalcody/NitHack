package com.example.sahaya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Stud_Profile_Fragment extends Fragment {
AppCompatButton editStudent;
TextView name,identity,phone,college,address;
ImageView idcard;
    String identity_="",ph="",pass="";
    ImageView imgP;
    SharedPreferences sp1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_stud__profile_, container, false);
        editStudent=view.findViewById(R.id.bt_student);
        name=view.findViewById(R.id.name1_general_profile);
        identity=view.findViewById(R.id.identity_profile_general);
        imgP=view.findViewById(R.id.image_profile_general);
        phone=view.findViewById(R.id.phone_profile_general);
        idcard=view.findViewById(R.id.idcard_profile_student);
        college=view.findViewById(R.id.college_profile_general);
        address=view.findViewById(R.id.address_profile_general);
        sp1=getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        identity_=sp1.getString("identity","");
        ph=sp1.getString("phone","");

        name.setText(sp1.getString("name44",""));
        Bitmap b = getbitmap(sp1.getString("bitmap",""));
        Bitmap c=getbitmap(sp1.getString("idcard",""));
        imgP.setImageBitmap(b);
        idcard.setImageBitmap(c);
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