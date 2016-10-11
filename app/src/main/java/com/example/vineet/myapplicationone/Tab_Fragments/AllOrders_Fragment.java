package com.example.vineet.myapplicationone.Tab_Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vineet.myapplicationone.Adapters.RecycleAdapter;
import com.example.vineet.myapplicationone.Models.ListItems;
import com.example.vineet.myapplicationone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
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
//    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag_view = inflater.inflate(R.layout.bob_allorders_fragment,null);
        listitemto = new ArrayList<ListItems>();

        Fango_url asyncTask = new Fango_url();
        asyncTask.execute("http://52.66.140.142:8080/fango_live/admin/order/get/all");
        recyclerView = (RecyclerView) frag_view.findViewById(R.id.bob_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        layoutManager = new LinearLayoutManager(getActivity());





        new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                // do whateverTo
             //   Toast.makeText(getActivity(),)
            }

            @Override public void onLongItemClick(View view, int position) {
                // do whatever
            }
        });
        return frag_view;

    }


    public class Fango_url extends AsyncTask<String, Void ,Boolean> {

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
                while((line = reader.readLine())!=null){
                    buffer.append(line);
                }
                String finaljson = buffer.toString();
                JSONArray jArray = new JSONArray(finaljson);
                if (jArray ==null) {

                }
                for (int i=0; i<jArray.length();i++){
                    ListItems listItems = new ListItems();
                    JSONObject jObject = jArray.getJSONObject(i);
                    listItems.setFango_name(jObject.getString("name"));
                    listItems.setFango_location(jObject.getString("address"));
                    listItems.setFango_order_id(jObject.getString("order_code"));
                    listItems.setFango_no_of_items(jObject.getString("no_of_items"));
                    listItems.setFango_total_amount(jObject.getString("total_amount"));

                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(Long.parseLong(jObject.getString("order_time")));
                    Date date = (Date) c.getTime();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                    String time = format.format(date);
                    listItems.setFango_time(time);
                    Log.d("list ",":"+listItems);

                    listitemto.add(listItems);


                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Error is ", ": "+e);
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("Another url error"," :" +e);
                }

            }
            return false;
        }
        @Override
        protected void onPostExecute(Boolean s) {
            Log.d("listttt", "isssss :" + listitemto.size());
            recycleAdapter = new RecycleAdapter(listitemto);
            recyclerView.setAdapter(recycleAdapter);
//            recycleAdapter.notifyDataSetChanged();

        }
    }
}
