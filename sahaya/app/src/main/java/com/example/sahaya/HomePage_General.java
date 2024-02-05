package com.example.sahaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomePage_General extends AppCompatActivity  {
DrawerLayout drawerLayout;
    TextView headerText,headerText2;
    ImageView headerimg;
    boolean back=false;
Toolbar toolbar;
    NavigationView nav;
    SharedPreferences sp,sp1;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_general);
        openFragment(new Stud_home_Fragment());
        sp=getSharedPreferences("users",MODE_PRIVATE);
        sp1=getSharedPreferences("login",MODE_PRIVATE);

        drawerLayout = findViewById(R.id.drawerLayout_general);
        toolbar = findViewById(R.id.toolbar_general);
        nav = findViewById(R.id.navigationview_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView=findViewById(R.id.navigationview_drawer);
        View headerView = navigationView.getHeaderView(0);
        headerText = headerView.findViewById(R.id.text1_general);
        headerText2 = headerView.findViewById(R.id.text2_general);
        headerimg = headerView.findViewById(R.id.img_general_header);
        headerText.setText(sp.getString("name44","user"));

            headerText2.setText(sp.getString("identity", "user"));
        try {
            Bitmap b = getbitmap(sp.getString("bitmap", ""));
            headerimg.setImageBitmap(b);

        }
        catch(Exception e){
           headerimg.setImageResource(R.drawable.user);
        }
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_profile) {
                     openFragment(new Stud_Profile_Fragment());
                    Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_SHORT).show();
                }
                else if (itemId==R.id.nav_home) {
                      openFragment(new Stud_home_Fragment());
                }
                else if(itemId==R.id.nav_QRcode){
                    openFragment(new Stud_QR_Fragment());

                }else if(itemId==R.id.nav_setting) {
                    openFragment(new Stud_Setting_Fragment());
                    }
                else if (itemId==R.id.nav_logout) {
                    Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                    sp1.edit().putBoolean("islogged",false).apply();

                    Toast.makeText(getApplicationContext(), "logging out", Toast.LENGTH_SHORT).show();
                    startActivity(i);


                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            if(this.getSupportFragmentManager().getBackStackEntryCount()==1 && !back){
                back=true;
                Toast.makeText(getApplicationContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();

            }
            else if(this.getSupportFragmentManager().getBackStackEntryCount()==1 && back){

                finishAffinity();
            }

            else {
                super.onBackPressed();
            }
        }
    }
    private void openFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction().addToBackStack("tag");;
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }
    public Bitmap getbitmap(String s){
        byte[] bytes= Base64.decode(s,Base64.DEFAULT);
        Bitmap bitmap2= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        return bitmap2;
    }



}