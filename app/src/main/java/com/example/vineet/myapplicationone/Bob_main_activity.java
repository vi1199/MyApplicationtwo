package com.example.vineet.myapplicationone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by Vineet on 10/6/2016.
 */

public class Bob_main_activity extends AppCompatActivity {
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
//    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bob_activity_main);



 //       drawerLayout = (DrawerLayout) findViewById(R.id.bob_drawer_id);
   //     navigationView = (NavigationView) findViewById(R.id.bob_nav_drawer);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bob_container_view, new TabFragment()).commit();

        toolbar = (Toolbar) findViewById(R.id.bob_widget_toolbar);
        setSupportActionBar(toolbar);


//        actionBarDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
//
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                drawerLayout.closeDrawers();
//
//                if (item.getItemId() == R.id.bob_help) {
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.bob_container_view,new TabFragment()).commit();
//
//                }
//
//                if (item.getItemId() == R.id.bob_settings) {
//                    FragmentTransaction xfragmentTransaction = fragmentManager.beginTransaction();
//                    xfragmentTransaction.replace(R.id.bob_container_view,new TabFragment()).commit();
//                }
//                return false;
//            }
//        });
//
   }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bob_menu, menu);
//
//        final MenuItem searchItem = menu.findItem(R.id.bob_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//       searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
//        return true;
//    }
//
}

