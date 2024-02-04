package com.example.sahaya;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class QRScanner extends AppCompatActivity {
AppCompatButton scan;

ProgressBar progressBar;
LinearLayout  occupation_parent,roll_parent,address_parent,idcard_parent;
ScrollView scrollView;
TextView txt,name,college,roll,phone33,gender,address,occupation,user;
ImageView img,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        scan=findViewById(R.id.scan_qrcode);
        occupation_parent=findViewById(R.id.occupation_qrscan_parent);
        roll_parent=findViewById(R.id.Roll_qrscan_parent);
        address_parent=findViewById(R.id.address_qrscan_parent);
        idcard_parent=findViewById(R.id.idcard_qrscan_parent);
        progressBar=findViewById(R.id.qrscan_guard_progress);
        user=findViewById(R.id.user_qrscan);
        scrollView=findViewById(R.id.qrscan_scroll);
        scrollView.setVisibility(View.GONE);
        img=findViewById(R.id.imgs);
        img2=findViewById(R.id.idcard_qrscan);
        occupation=findViewById(R.id.occupation_qrscan);
        name=findViewById(R.id.name_qrscan);
        college=findViewById(R.id.collge_qrscan_name);
        roll=findViewById(R.id.roll_qrscan);
        gender=findViewById(R.id.gender_qrscan);
        address=findViewById(R.id.address_qrscan);
        phone33=findViewById(R.id.phone_qrscan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator i=new IntentIntegrator(QRScanner.this);
                i.setOrientationLocked(false);
                i.setPrompt("Scan QR Code");
                i.setDesiredBarcodeFormats(i.QR_CODE);
                i.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult r = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (r != null) {
            String content = r.getContents();
            if (content != null) {
                String[] arr = content.split("//");

                try {

                    auth(arr[0], arr[1], arr[2]);
                }catch (Exception e){

                    Toast.makeText(getApplicationContext(),"wrong qrcode",Toast.LENGTH_SHORT).show();
                }


            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }


    public void auth(String phone, String password, String identity){
        progressBar.setVisibility(View.VISIBLE);
        scan.setVisibility(View.GONE);

        String temp="";
        if(identity.equals("Student")) {
            temp = "https://securityProject.onrender.com/api/student/auth";
        }
        else if(identity.equals("Faculity Member")){
            temp = "https://securityProject.onrender.com/api/faculity/auth";
        }
        else if(identity.equals("Admin")){
            temp = "https://securityProject.onrender.com/api/admin/auth";
        }
        else if(identity.equals("Guard")){
            temp = "https://securityProject.onrender.com/api/guard/auth";
        }

        HashMap<String,String> jsonobj=new HashMap<>();
        jsonobj.put("password", password);
        jsonobj.put("phone", phone);
        JsonObjectRequest j = new JsonObjectRequest(Request.Method.POST, temp,new JSONObject(jsonobj), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {
                    if(Boolean.parseBoolean(response.getString("success"))) {

                        Bitmap b = getbitmap(response.getString("photo"));
                        img.setImageBitmap(b);
                        name.setText(response.getString("username"));
                        college.setText(response.getString("college"));
                         phone33.setText(phone);
                         user.setText(identity);
                        gender.setText(response.getString("gender"));


                        if (identity.equals("Student")) {
                            Bitmap b2 = getbitmap(response.getString("photoid"));
                            img2.setImageBitmap(b2);
                            roll.setText(response.getString("roll"));

                            occupation_parent.setVisibility(View.GONE);
                            roll_parent.setVisibility(View.VISIBLE);
                            address.setText(response.getString("hostel"));
                            address_parent.setVisibility(View.VISIBLE);

                        } else if (identity.equals("Admin")) {
                            Bitmap b2 = getbitmap(response.getString("photoid"));
                            img2.setImageBitmap(b2);
                            occupation_parent.setVisibility(View.GONE);
                            roll_parent.setVisibility(View.GONE);
                            address.setText(response.getString("hostel"));
                            address_parent.setVisibility(View.VISIBLE);

                        } else if (identity.equals("Guard")) {
                            occupation_parent.setVisibility(View.GONE);
                            roll_parent.setVisibility(View.GONE);
                            idcard_parent.setVisibility(View.GONE);
                            address_parent.setVisibility(View.GONE);
                        } else if (identity.equals("Faculity Member")) {
                            Bitmap b2 = getbitmap(response.getString("photoid"));
                            roll_parent.setVisibility(View.GONE);
                            address_parent.setVisibility(View.GONE);
                            img2.setImageBitmap(b2);
                            occupation.setText(response.getString("specification"));


                        }

                        progressBar.setVisibility(View.GONE);
                        img.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.VISIBLE);

                    }
                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    scan.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                    img.setVisibility(View.GONE);
                    throw new RuntimeException(e);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                scan.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
            }
        });
        RequestQueue q= Volley.newRequestQueue(QRScanner.this);

        q.add(j);

    }

    public Bitmap getbitmap(String s){
        byte[] bytes=Base64.decode(s,Base64.DEFAULT);
        Bitmap bitmap2=BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        return bitmap2;
    }

}