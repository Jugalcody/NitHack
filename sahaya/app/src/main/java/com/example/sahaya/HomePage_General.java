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
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class HomePage_General extends AppCompatActivity  {
DrawerLayout drawerLayout;
Toolbar toolbar;
    NavigationView nav;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_general);

        drawerLayout = findViewById(R.id.drawerLayout_general);
        toolbar = findViewById(R.id.toolbar_general);
        nav = findViewById(R.id.navigationview_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        openFragment(new Stud_home_Fragment());
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
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }

}