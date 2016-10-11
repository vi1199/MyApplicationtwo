package com.example.vineet.myapplicationone;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vineet.myapplicationone.Adapters.ListAdapter;
import com.example.vineet.myapplicationone.Adapters.RecycleAdapter;
import com.example.vineet.myapplicationone.Adapters.ViewPageAdapter;
import com.example.vineet.myapplicationone.Models.ListItems;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public final static String KEY_ME = "data";
    ArrayList<ListItems> listitemto;
    RecyclerView recyclerView;
    private UserDetails userDetails;
    private ListItems parselist;
    RecycleAdapter recycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_main_layout);


        recyclerView = (RecyclerView) findViewById(R.id.frag_layout_scroll);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        makeParselData();

        Toolbar tool = (Toolbar) findViewById(R.id.newtoolbar);
        setSupportActionBar(tool);

//        final ViewPager viewPager = (ViewPager) findViewById(R.id.new_view_pager);
//        setupViewPager(viewPager);

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.new_tab_layout);
//        tabLayout.setupWithViewPager(viewPager);
//        listitemto = new ArrayList<ListItems>();
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 0:
//                        viewPager.setCurrentItem(tab.getPosition());
//                        Log.d("Tag ", " is :" + viewPager);
                        // Toast.makeText(getApplicationContext(),"One",Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1:
//                          Toast.makeText(getApplicationContext(),"Two",Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                          Toast.makeText(getApplicationContext(),"Three",Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });
        Webjons webjons = new Webjons();
        webjons.execute("http://52.66.140.142:8080/fango_live/admin/order/get/all");
    }

    private void makeParselData() {
        parselist = new ListItems();
        parselist.getFango_name();
    }

//    public void setupViewPager(ViewPager upViewPager) {
//        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
//        adapter.addFrag(new tabFragment(getResources().getColor(R.color.ripple_material_light)), "All");
//        adapter.addFrag(new tabFragment(getResources().getColor(R.color.ripple_material_light)), "Current Week ");
//        adapter.addFrag(new tabFragment(getResources().getColor(R.color.ripple_material_light)), "Previous Week");
//        upViewPager.setAdapter(adapter);
//    }

    private class Webjons extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONArray jsonarray = new JSONArray(finaljson);

                if (jsonarray == null) {

                }

                Log.d("array", "is : " + jsonarray);
                for (int i = 0; i < jsonarray.length(); i++) {

                    ListItems list = new ListItems();
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    list.setFango_name(jsonobject.getString("name"));
                    list.setFango_location(jsonobject.getString("address"));
                    list.setFango_order_id(jsonobject.getString("order_code"));


                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(Long.parseLong(jsonobject.getString("order_time")));
                    Date date = (Date) c.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                    String time = format.format(date);
                    list.setFango_time(time);

                    list.setFango_no_of_items(jsonobject.getString("no_of_items"));
                    list.setFango_total_amount(jsonobject.getString("total_amount"));
                    listitemto.add(list);
                    Log.d("items are ", ":" + listitemto);
                }
            } catch (Exception e) {
                Log.d("url error", "error");
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean s) {
            Log.d("listttt", "is :" + listitemto.size());
            recycleAdapter = new RecycleAdapter(listitemto);
            recyclerView.setAdapter(recycleAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_custom_toolbar, menu);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
//        searchView = null;
//        if (searchView != null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        } else {
//            Log.d("meee", "mm" + searchView);
//        }
        return true;
    }
}

