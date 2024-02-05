package com.example.sahaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sp,sp1;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp=getSharedPreferences("users",MODE_PRIVATE);
        sp1=getSharedPreferences("login",MODE_PRIVATE);
        user=sp1.getString("identity","");

        boolean islogged=sp1.getBoolean("islogged",false);
        if(!islogged) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }
            },4000);

        }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

              if(user.equals("Student") || user.equals("Faculit Member")) {
                  Intent i = new Intent(SplashScreen.this, HomePage_General.class);
               startActivity(i);
              }
              else if(user.equals("Guard")) {
                  Intent i = new Intent(SplashScreen.this, Security.class);
                  startActivity(i);
              }
              else if(user.equals("Admin")){
                  Intent i = new Intent(SplashScreen.this, Admin.class);
                  startActivity(i);
              }
                    finish();
                }

            },1160);
        }


    }}