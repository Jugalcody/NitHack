package com.example.sahaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class HomepageGeneral extends AppCompatActivity {
   DrawerLayout drawerLayout;
   NavigationView navigationView;
   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
       drawerLayout.addDrawerListener(toggle);
       toggle.syncState();

        openFragment(new Stud_home_Fragment());
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int itemId= item.getItemId();
               if(itemId==R.id.nav_home){
                  openFragment(new Stud_home_Fragment());
               } else if (itemId==R.id.nav_QRcode) {
                    openFragment(new Stud_QR_Fragment());
               }
                else if (itemId==R.id.nav_profile) {
                     openFragment(new Stud_Profile_Fragment());
               }
                else if (itemId==R.id.nav_setting) {
                   openFragment(new Stud_Setting_Fragment());
               }
               drawerLayout.closeDrawer(GravityCompat.START);
               return true;
           }
       });

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void openFragment(Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.container,fragment);
        ft.commit();

    }
}