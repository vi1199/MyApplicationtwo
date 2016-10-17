package com.example.vineet.myapplicationone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.vineet.myapplicationone.Adapters.DetailsViewAdapter;
import com.example.vineet.myapplicationone.Adapters.RecycleAdapter;
import com.example.vineet.myapplicationone.Models.ListItems;
import com.example.vineet.myapplicationone.Models.Details_with_photos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vineet on 10/8/2016.
 */

public class Bob_order_onclick extends AppCompatActivity {
    ArrayList<Details_with_photos> returnlist;
    DetailsViewAdapter returnAdapter;
    RecyclerView recyclerView;
    TextView name, id,address_, amount,no_of_items;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bob_order_details);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bob_recycle_view);
        recyclerView.setLayoutManager(layoutManager);


        name = (TextView)findViewById(R.id.bob_details_name);
        id= (TextView)findViewById(R.id.bob_details_order_id);
        address_ = (TextView)findViewById(R.id.bob_details_address);
        amount = (TextView)findViewById(R.id.bob_details_amount);
        no_of_items = (TextView) findViewById(R.id.bob_details_no_of_items);
        returnlist = new ArrayList<Details_with_photos>();

        Intent in = getIntent();
        String nameMe= in.getStringExtra("key");
        String orderId = in.getStringExtra("key2");
        name.setText(nameMe);
        id.setText(orderId);

        OrderJson orderJson = new OrderJson();
        orderJson.execute("http://52.66.140.142:8080/fango_live/admin/order/get?order_id="+orderId);
    }

    public class OrderJson extends AsyncTask<String,Void,Boolean> {

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
                String returnJson = buffer.toString();
//                JSONArray returnarray = new JSONArray(returnJson);
//                if (returnarray == null) {
//                    do something
//                }
//                for (int i = 0; i < returnarray.length(); i++) {
                    Details_with_photos details = new Details_with_photos();
//                    JSONObject jObject = new JSONObject(returnJson);
                JSONObject jsonObject=new JSONObject(returnJson);
                JSONArray jsonArray=jsonObject.getJSONArray("order_products");
                    Log.d("array is ",":"+jsonObject);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject cur = jsonArray.getJSONObject(i);
                    details.setDetails_opid_color(cur.getString("color"));
                    details.setDetails_size(cur.getString("size"));
                    details.setDetails_price(cur.getString("price"));
                    returnlist.add(details);

                    Log.d("op id","is:"+details);
                }


//                    JSONArray newjson = jObject.getJSONArray("order_products");
//                    details.setDetails_name(jsonArray.getString("name"));

//                Log.d("op code ","is:"+details);
//                    details.setDetails_order_code(jObject.getString("order_code"));
//                    details.setDetails_total_amount(jObject.getString("price"));
//                    details.setDetails_product_pic_url(jObject.getString("product_pic_url"));
//                    details.setDetails_address(jObject.getString("address"+"locality"));
//
//

//                    Calendar c = Calendar.getInstance();
//                    c.setTimeInMillis(Long.parseLong(jObject.getString("order_time")));
//                    Date date = (Date) c.getTime();
//                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"+"dd/MM/yyyy");
//                    String time = format.format(date);
//
//
//
//                    date.compareTo(date);
//                    details.setDetails_time(time);
//                    Log.d("return list is",":"+details);
//
//                   returnlist.add(details);


                } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
//            } catch (MalformedURLException e1) {
//                e1.printStackTrace();
//            }

        } catch (Exception e) {
                e.printStackTrace();
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
                    Log.d("Another url error", " :" + e);
                }

            }
            return false;
        }
        @Override
        protected void onPostExecute(Boolean s) {
            Log.d("listttt", "isssss :" + returnlist.size());
            returnAdapter = new DetailsViewAdapter(returnlist);
            recyclerView.setAdapter(returnAdapter);
        }
    }
}

