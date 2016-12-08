package com.example.vineet.myapplicationone.Tab_Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vineet.myapplicationone.Adapters.RecycleAdapter;
import com.example.vineet.myapplicationone.Bob_main_activity;
import com.example.vineet.myapplicationone.Bob_order_onclick;
import com.example.vineet.myapplicationone.CallingConstants.ConstantsCall;
import com.example.vineet.myapplicationone.Models.ListItems;
import com.example.vineet.myapplicationone.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vineet on 10/6/2016.
 */

public class AllOrders_Fragment extends Fragment {
    ArrayList<ListItems> listitemto;
    RecycleAdapter recycleAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

//    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View frag_view = inflater.inflate(R.layout.bob_allorders_fragment, null);
        listitemto = new ArrayList<ListItems>();



         swipeRefreshLayout = (SwipeRefreshLayout) frag_view.findViewById(R.id.swipeme);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Fango_url asyncTask = new Fango_url();


                    asyncTask.execute(new ConstantsCall().getAll);

            }
        });

        Fango_url asyncTask = new Fango_url();
        asyncTask.execute(new ConstantsCall().getAll);
        recyclerView = (RecyclerView) frag_view.findViewById(R.id.bob_recycle_view);
        LinearLayoutManager lastlayoutmanager = new LinearLayoutManager(getContext());
//        lastlayoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lastlayoutmanager);
        recyclerView.setOnScrollListener(new EndlessRecyclerViewOnScrollListener(lastlayoutmanager) {
            @Override
            public void onLoadMore(int current_page) {
                View All = inflater.inflate(R.layout.progress_bar,null);
                Toast.makeText(getContext(),"this is it ",Toast.LENGTH_LONG).show();
            }
        });


        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent intent = new Intent(v.getContext(),Bob_order_onclick.class);
                        intent.putExtra("key", listitemto.get(position).getFango_name());
                        intent.putExtra("key2",listitemto.get(position).getFango_order_id());
                        intent.putExtra("key3",listitemto.get(position).getFango_total_amount());
                        intent.putExtra("key4",listitemto.get(position).getFango_no_of_items());
                        intent.putExtra("key5",listitemto.get(position).getFango_city());
                        startActivity(intent);
                    }
                });

        return frag_view;

    }



    public class Fango_url extends AsyncTask<String, Void, Boolean> {



        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
                JSONArray jArray = new JSONArray(finaljson);
                if (jArray == null) {

                }

                for (int i = 0; i < jArray.length(); i++) {
                    ListItems listItems = new ListItems();
                    JSONObject jObject = jArray.getJSONObject(i);
                    JSONObject FirstArray = jArray.getJSONObject(0);
                    String topFirst = FirstArray.toString();

                    listItems.setFango_name(jObject.getString("name"));
                    listItems.setFango_fango_id(jObject.getString("order_code"));
                    listItems.setFango_order_id(jObject.getString("order_id"));
                    listItems.setFango_no_of_items(jObject.getString("no_of_items"));
                    listItems.setFango_total_amount(jObject.getString("total_amount"));
                    listItems.setFango_city(jObject.getString("city"));


                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(Long.parseLong(jObject.getString("order_time")));
                    Date date =c.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                    String time = format.format(date);



//                    listItems.setFango_time(time);

//                    Log.d("list ", ":" + listItems);
//                    Collections.sort(listitemto);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(date);
//                    calendar.add(Calendar.DAY_OF_WEEK,-7);
//                    Date newdate = calendar.getTime();
                    listItems.setFango_time(String.valueOf(time));

                    listitemto.add(listItems);

//                    Log.d("list items are ",":"+listitemto);
                }

            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(getActivity()," UnAble to fetch data, check Connection", Toast.LENGTH_LONG).show();
                Log.d("Error is ", ": " + e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Server Offline , Try After Some Time", Toast.LENGTH_LONG).show();
                    Log.d("Another url error", " :" + e);
                }

            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean s) {
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
//            Log.d("listttt", "isssss :" + listitemto.size());
            recycleAdapter = new RecycleAdapter(listitemto);
            recyclerView.setAdapter(recycleAdapter);

//            recycleAdapter.notifyDataSetChanged();





        }
    }
}
