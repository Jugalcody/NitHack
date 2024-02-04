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

public class Security extends AppCompatActivity {
    DrawerLayout drawerLayout;
    SharedPreferences sp1;
    Toolbar toolbar;
    TextView headerText,headerText2;
    ImageView headerimg;
    NavigationView nav;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        drawerLayout = findViewById(R.id.drawerLayout_security);
        toolbar = findViewById(R.id.toolbar_security);
        nav = findViewById(R.id.navigationview_drawer_security);
        sp1=getSharedPreferences("users",MODE_PRIVATE);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView=findViewById(R.id.navigationview_drawer_security);
        View headerView = navigationView.getHeaderView(0);
        headerText = headerView.findViewById(R.id.text1_guard);
        headerText2 = headerView.findViewById(R.id.text2_guard);
        headerimg = headerView.findViewById(R.id.img_guard);
        headerText.setText(sp1.getString("name44","user"));
        headerText2.setText(sp1.getString("identity","user"));

        Bitmap b = getbitmap(sp1.getString("bitmap",""));
        headerimg.setImageBitmap(b);

        openFragment(new Guard_Home_Fragment());
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_profile) {
                    openFragment(new Guard_Profile_Fragment());
                    Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_SHORT).show();
                }
                else if (itemId==R.id.nav_home) {
                    openFragment(new Guard_Home_Fragment());
                }
                else if(itemId==R.id.nav_QRcode){
                      openFragment(new Guard_QR_Fragment());
                }else if(itemId==R.id.nav_setting) {
                    openFragment(new Guard_Setting_fragment());
                }
                else if (itemId==R.id.nav_logout) {
                    Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "logging out", Toast.LENGTH_SHORT).show();
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
        } else {

            super.onBackPressed();

        }
    }
    private void openFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_security,fragment);
        transaction.commit();
    }

    public Bitmap getbitmap(String s){
        byte[] bytes= Base64.decode(s,Base64.DEFAULT);
        Bitmap bitmap2= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        return bitmap2;
    }
}