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

    private TextView textView ;
    public final static String KEY_ME = "data";
    ListView listView;
    ListAdapter listAdapter;
    ArrayList<ListItems> listitemto;
    RecyclerView recyclerView;
    private UserDetails userDetails;
    private ListView drawerlist;
    ArrayAdapter<String> drawerAdapter;
    private ListItems parselist;
    RecycleAdapter recycleAdapter;



 //   ArrayList<String> items = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_main_layout);
        drawerlist = (ListView) findViewById(R.id.navList) ;


        recyclerView = (RecyclerView) findViewById(R.id.recyclenew);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(listitemto);
        recyclerView.setAdapter(recycleAdapter);

        makeParselData();

        Toolbar tool = (Toolbar) findViewById(R.id.newtoolbar);
        setSupportActionBar(tool);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.new_view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.new_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

//        listitemto = new ArrayList<ListItems>();
//        Log.d("list is"," : "+listitemto);
//        recycleAdapter  = new RecycleAdapter(getApplicationContext(),R.layout.uitextlist,listitemto);
//        recyclerView.setAdapter(recycleAdapter);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                switch (tab.getPosition()) {
                    case 0:
//                        tabFragment fragment = new tabFragment();

                        viewPager.setCurrentItem(tab.getPosition());
                        Log.d("Tag "," is :" +viewPager);
                    //    RecycleAdapter newdat= new RecycleAdapter(viewPager);
//                        Log.d("here" ," is : "+viewPager.getAdapter());
            //            Toast.makeText(getApplicationContext(),viewPager.getAdapter(),Toast.LENGTH_LONG).show();



                       // Toast.makeText(getApplicationContext(),"One",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                      //  Toast.makeText(getApplicationContext(),"Two",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                      //  Toast.makeText(getApplicationContext(),"Three",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });






//        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//                                               listView = (ListView) findViewById(R.id.list_item) ;
//                                                  listAdapter = new ListAdapter(getApplicationContext(),R.layout.uitextlist, listitemto);
//                                               listView.setAdapter(listAdapter);
//                                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {



//                String text = parent.getItemAtPosition(position).toString();
//                Toast.makeText(getApplication(),"selected ext is :" +text,Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), UserDetails.class);
//                intent.putExtra("yourItem", text);
//                startActivity(intent);
//
//                Log.d("selected items","are : " +text);
           //    ListItems listItems = null;
//              String[] values = new String[3];
//                String[0]= listItems.getFango_name();
//                String[1] = listItems.getFango_location();
//                String[2] = listItems.getFango_order_id();

//                Bundle bundle = new Bundle();
//               bundle.putParcelable(KEY_ME, listitemto.get(position));
//
//                Intent intent = new Intent(getApplicationContext(), UserDetails.class);
//
//                intent.putExtras(bundle);
//                Log.d("data","new :"+ bundle );
//                startActivity(intent);
//            }
//        });

        Webjons webjons = new Webjons();
        webjons.execute("http://52.66.140.142:8080/fango_live/admin/order/get/all");


    }

    private void makeParselData() {

        parselist = new ListItems();
        parselist.getFango_name();
    }

    private String convertTime (String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm aa MM-dd");
        java.util.Date date = null;
        try {
            date = format.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String newDate = format1.format(date);
        return newDate;
    }

    public void setupViewPager(ViewPager upViewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.addFrag(new tabFragment(getResources().getColor(R.color.accent_material_light)), "All");
        adapter.addFrag(new tabFragment(getResources().getColor(R.color.ripple_material_light)), "Current Week ");
        adapter.addFrag(new tabFragment(getResources().getColor(R.color.button_material_dark)), "Previous Week");
        upViewPager.setAdapter(adapter);

     //   this.upViewPager = upViewPager;
    }




    private class Webjons extends AsyncTask<String ,Void,Boolean> {

             @Override
             protected Boolean doInBackground(String... params) {
                 HttpURLConnection connection = null;

                 BufferedReader reader= null;
                 try
                 {
                     URL url = new URL(params[0]);


                     connection = (HttpURLConnection) url.openConnection();
                     connection.connect();
                     InputStream stream = connection.getInputStream();

                     reader = new BufferedReader(new InputStreamReader(stream));
                     StringBuffer buffer= new StringBuffer();
                     String line = "";
                     while ((line = reader.readLine()) != null) {
                         buffer.append(line);
                     }
                     String finaljson = buffer.toString();
                     JSONArray jsonarray = new JSONArray(finaljson);

                     Log.d("array","is : " +jsonarray);


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
                       //  list.setFango_photo(jsonobject.getString("order_photo"));




                         listitemto.add(list);
                         Log.d("items are ",":"+listitemto);


                       //  Log.d("List people " , " List : " +listitemto);
//                         String user_id = jsonobject.getString("user_id");
//                         String order_id = jsonobject.getString("order_id");
//                         String order_code = jsonobject.getString("order_code");
//                         String name = jsonobject.getString("name");
//                         String address = jsonobject.getString("address");
//                         Log.d("userid" , "User id " +user_id);
//                         int id = jsonobject.getInt("id");
//                         String tyler = jsonobject.getString("order_id");
//                         String Oscar = jsonobject.getString("name");
//                         items.add("user : "+name);
//                         items.add("order_id : "+ tyler);
//                         items.add("user_id : "+user_id);
//                         items.add("address : "+address);
//                         items.add ("order_code : "+order_code);
 //                      Log.d("userid" , "User id " +tyler);
                      //   return true;
                    }
//                     JSONArray json = new JSONArray(finaljson);
//
//                     for(int i=0;i<json.length();i++){
//                         HashMap<String, String> map = new HashMap<String, String>();
//                         JSONObject e = json.getJSONObject(i);
//
//                         map.put("order_id",  String.valueOf(i));
//                         map.put("name", "username:" + e.getString("name"));
//                         map.put("user_id", "userid " +  e.getString("user_id"));
//                         listView.add(map);
//                     }
//


                 }
                 catch (Exception e ) {
                     Log.d("url error","error" );
                     Toast.makeText(getApplicationContext(),"Cannot Fetch Data- Database Empty",Toast.LENGTH_LONG).show();

                 }
                 finally {
                     if(connection != null) {
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
//                 Log.d("listttt","is :"+listitemto.size());
//                 recycleAdapter = new RecycleAdapter(listitemto);
//                 recyclerView.setAdapter(recycleAdapter);
//                 RecycleAdapter recycleAdapter = new RecycleAdapter(getApplicationContext(),R.layout.uitextlist,listitemto);
//                 Log.d("List people " , " List : " +listitemto.size());
//                 recyclerView.setAdapter(recycleAdapter);
//              recycleAdapter.notifyDataSetChanged();
               //  RecycleAdapter.notifyDataSetChanged();
 //                ListAdapter adapter = new ListAdapter(getApplicationContext(), R.layout.text_list,listitemto);
////                 ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.text_list,items);
//                 listView.setAdapter(mArrayAdapter);

             }
         }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_toolbar, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView = null;
        if (searchView !=null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        else
        {
            Log.d("meee","mm"+searchView);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    }

