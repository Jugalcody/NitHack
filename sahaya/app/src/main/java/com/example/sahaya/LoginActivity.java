package com.example.sahaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
    TextView signup;
    SharedPreferences sp1;
    ProgressBar progressBar;
    SharedPreferences sp;
    Spinner spinner;
    String user="null";
    String bitmap="";
    EditText phone,pass;
    AppCompatButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone=findViewById(R.id.phone);
        pass=findViewById(R.id.pass);
        signup=findViewById(R.id.regclick);
        spinner=findViewById(R.id.loginSpinner);
        login =findViewById(R.id.login);
        sp=getSharedPreferences("qr",MODE_PRIVATE);
        progressBar=findViewById(R.id.progress_login);
        progressBar.setVisibility(View.GONE);
        sp1=getSharedPreferences("users",MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone_=phone.getText().toString().trim();
                String pass_=pass.getText().toString().trim();
                if(user.equals("null")){
                    Toast.makeText(LoginActivity.this, "select empty!", Toast.LENGTH_SHORT).show();
                }
                else if(phone_.equals("")){
                    Toast.makeText(LoginActivity.this, "Phone no. empty!", Toast.LENGTH_SHORT).show();
                }
                else if(pass_.equals("")){
                    Toast.makeText(LoginActivity.this, "password empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    auth(phone_,pass_,user);
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String[] st = {"Select", "Student", "Faculity Member","Admin","Security Guard"};
        RegisterSpinnerApdater adapter = new RegisterSpinnerApdater(this, R.layout.spinner1, st);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedOption = (String) adapterView.getItemAtPosition(i);

                if (selectedOption.equals("Student")) {
                    user="Student";
                }
                else if (selectedOption.equals("Faculity Member")) {
                    user="Faculity Member";

                }
                else if (selectedOption.equals("Select"))
                {
                    user="null";
                }
                else if(selectedOption.equals("Security Guard")){
                    user="Guard";
                }
                else if(selectedOption.equals("Admin")){
                    user="Admin";
                }
                else{
                    user="null";
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                user="null";
            }
        });


    }


    public void auth(String phone, String password, String identity){
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
                progressBar.setVisibility(View.GONE);
                try {
                    if(Boolean.parseBoolean(response.getString("success"))){
sp1.edit().putString("phone",phone).apply();
sp1.edit().putString("pass",password).apply();
sp1.edit().putString("identity",identity).apply();
                        sp1.edit().putString("bitmap",response.getString("photo")).apply();
                        sp1.edit().putString("name44",response.getString("username")).apply();
                        sp1.edit().putString("college",response.getString("college")).apply();
                        sp1.edit().putString("gender",response.getString("gender")).apply();
                        Toast.makeText(getApplicationContext(),response.getString("msg"), Toast.LENGTH_SHORT).show();
                  if(identity.equals("Student")) {
                      Intent b1 = new Intent(LoginActivity.this, HomePage_General.class);
                      sp1.edit().putString("roll",response.getString("roll")).apply();
                      sp1.edit().putString("address",response.getString("hostel")).apply();
                      sp1.edit().putString("idcard",response.getString("photoid")).apply();
                      startActivity(b1);
                  }
                  else if(identity.equals("Admin")) {
                      Intent b1 = new Intent(LoginActivity.this, Admin.class);

                      sp1.edit().putString("address",response.getString("hostel")).apply();
                      sp1.edit().putString("idcard",response.getString("photoid")).apply();
                      startActivity(b1);
                  }
                  else if(identity.equals("Guard")) {
                      Intent b1 = new Intent(LoginActivity.this, Security.class);
                      startActivity(b1);
                  }
                  else if(identity.equals("Faculity Member")) {
                      Intent b1 = new Intent(LoginActivity.this, HomePage_General.class);
                      sp1.edit().putString("idcard",response.getString("photoid")).apply();
                      sp1.edit().putString("work",response.getString("specification")).apply();
                      startActivity(b1);


                  }
                    }
                    else{

                        Toast.makeText(getApplicationContext(),response.getString("msg"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    throw new RuntimeException(e);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
        RequestQueue q= Volley.newRequestQueue(LoginActivity.this);

        q.add(j);

    }
}