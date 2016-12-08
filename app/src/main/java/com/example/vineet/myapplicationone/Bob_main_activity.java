package com.example.vineet.myapplicationone;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;

import com.example.vineet.myapplicationone.Adapters.RecycleAdapter;
import com.example.vineet.myapplicationone.CallingConstants.ConstantsCall;
import com.example.vineet.myapplicationone.Models.ListItems;
import com.example.vineet.myapplicationone.Tab_Fragments.AllOrders_Fragment;

import java.util.ArrayList;

/**
 * Created by Vineet on 10/6/2016.
 */

public class Bob_main_activity extends AppCompatActivity {
        DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    RecycleAdapter recycleAdapter;
    FragmentTransaction fragmentTransaction;
    ArrayList<ListItems> itemlist = new ArrayList<>();
    Toolbar toolbar;
    RecyclerView mrecycle;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bob_activity_main);

        toolbar = (Toolbar) findViewById(R.id.bob_widget_toolbar);
        setSupportActionBar(toolbar);


               drawerLayout = (DrawerLayout) findViewById(R.id.bob_drawer_id);
             navigationView = (NavigationView) findViewById(R.id.bob_nav_drawer);
        navigationView.setItemIconTintList(null);

       fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bob_container_view, new TabFragment()).commit();



        getSupportActionBar().setTitle("Fango Orders");



        actionBarDrawerToggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
//
//
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();

                if (item.getItemId() == R.id.bob_dhelivery) {
                    ConstantsCall.mainurl=ConstantsCall.fango_live;
                    Log.d("live url is ","+++++++++++"+new ConstantsCall().getAll);
                    getSupportActionBar().setTitle("Fango LIVE");
                    getSupportActionBar().setSubtitle("Web & Phone");
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.bob_container_view,new TabFragment()).commit();

                }

                if (item.getItemId() == R.id.bob_fedex) {
                    ConstantsCall.mainurl=ConstantsCall.fango_admin;
                    Log.d("new url is ","-----"+new ConstantsCall().getAll);
                    getSupportActionBar().setTitle("Fango ADMIN");
                    getSupportActionBar().setSubtitle("Social Network");
                    FragmentTransaction xfragmentTransaction = fragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.bob_container_view,new TabFragment()).commit();

                }
                return false;
            }
        });

    }


    @Override
            public boolean onCreateOptionsMenu(Menu menu){
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.bob_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.bob_search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                Log.d("new tes ","Search------"+newText);
                ArrayList<ListItems> items = null;

                for (ListItems listme : itemlist )
                {
                    Log.d("listme","Search is -----"+listme);
                    String name = listme.getFango_name().toLowerCase();
                    if (name.contains(newText))
                        items.add(listme);

                }

                recycleAdapter.setFilter(items);
                return true;
            }
        });
    return true;
}

}

